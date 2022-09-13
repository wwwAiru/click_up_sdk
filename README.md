# ClickUp SDK

## Инструкция по подключению для среды разработки: 

1. Склонировать/Обновить проект на своей машине 
2. Прописать `mvn clean install` для импорта проекта в локальный репозиторий Maven
3. Прописать зависимость в pom.xml:
```
<dependency>
   <groupId>ru.egartech</groupId>
   <artifactId>click-up-sdk-starter</artifactId>
   <version>0.0.1-SNAPSHOT</version>
</dependency>
```
4. application.yml:

```yaml
clickup:
  api:
    tokens: 
      - token1
      - token2
      - ...
  list:
    devs: "dev_list_id"
    analytics: "analytics_list_id"
    testers: "testers_list_id"
```

# Краткое описание SDK

## О SDK

Задача (далее Таска) - главная сущность в ClickUp API. Этот SDK создан для работы с Тасками. Содержимое таски делится на стандартное (`TaskDto`) и кастомные поля (`CustomField<?>`, далее кастом филды). Клиенты SDK реализуют API и дополняют новыми методами (к примеру: updateTask, об этом ниже). Главные интерфейсы: `TaskClient` и `CustomFieldClient`. Далее описания и примеры будут опираться на них (существует `ListTaskClient`, об этом ниже).

Для доступа в ClickUp нужен токен, но **каждый токен ограничен по числу запросов: 100 в минуту на каждый токен**. Поэтому токены передаются в качестве списка, где, затем, каждый новый запрос будет обновлять токен в карусели, тем самым не позволяя исчерпать лимит на каждый токен. Ошибки, связанные с исчерпанием лимита никак не обрабатываются, предполагается что будут использоваться разные токены. 

ClickUp API возвращает не очень приятные JSON объекты с огромной ветвистостью и разнообразием типов, потому - во введении опускается десериализация, однако нужно уточнить: сущности, приходящие из ClickUp изменены для большего удобства (**value всегда содержит полезную нагрузку**, остальные поля оставлены без изменений. Кастом филды хранятся по формату **FieldID** + **CustomField**). Ещё одна проблема - ClickUp принимает не те же объекты, что отдаёт (даже если оставить их без изменений) и в лучшем случае игнорирует, в худшем - отказывает в запросе, а это, при каскадном (об этом ниже) обновлении, является фатальной ошибкой, уничтожающей данные. Для борьбы с этим на создание и обновление таски используются разные кастом филды.

## О Тасках и Листах

Существует два (три) способа получить таску: 
1. По **TaskID**
2. По **CustomField** (об этом ниже)
3. Без **CustomField** (`TaskClient.getTasksByCustomFields(int listId)`)

**TaskID** каждой таски статичен, однако нигде не захардкожен: тасок много и они динамичны. Каждая таска хранится в своём Списке (далее лист). При поиске по кастомным филдам мы должны знать лист (чтобы знать где искать таску). **ListID** статичен, меняется только от среды к среде, обычно выносится в properties файл. Подводя итог, как мы ищем таски: 

* **ListID + CustomField** (нужно заранее знать ListID, занимает больше времени)
* **TaskID** (нужно заранее знать TaskID, самый быстрый)

## О Кастом филдах

Чаще всего мы не знаем **TaskID** перед запросом, поэтому - ищем по кастом филдам. Каждая таска имеет набор полей, созданных для среды, у каждого поля есть идентификатор (**FieldID**, статичен), имя (**Name**, нестатичен) и технические параметры. Получаемый кастом филд из кликапа десериализуется в удобный формат, доступ к каждому полю осуществляется с помощью метода: `TaskDto.customField(String fieldID)`. Метод содержит **Generic** аргумент, поэтому приводить типы не нужно. 

Для запроса, как и в тасках, используется другой вид объектов, выглядящий так: 

```json
{
  "field_id": "856d3fd6-b982-4085-aa67-65d60bf41bb7",
  "operator": "=",
  "value": 3
}
```

Операторов в ClickUp существует множество (полный список можно найти в документации ClickUp), но указанный выше самый распространённый. Этот запрос чем-то похож на SQL, и выглядел бы так: 

```sql
SELECT * FROM Tasks WHERE field_id = "856d3fd6-b982-4085-aa67-65d60bf41bb7", value = 3;
```

Воспроизводит такой JSON класс `BindFieldDto` (он имеет название Bind из-за того, что создавать кастомные филды нельзя, можно только обновлять их значения и добавлять созданные вручную филды к таске). По дефолту оператор `=` поэтому указывать его не нужно, достаточно указать **FieldId** и **Value**. Ниже приведён пример сервиса, который ищет все таски, где кастом филд `Номер` с **FieldID** `856d3fd6-b982-4085-aa67-65d60bf41bb7` равен какому-либо числу (предполагается что таска может быть в нескольких филдах, поэтому будет совершено несколько запросов):

```java
public class Service {

    private final TaskClient client;
    
    private final String myNumberFieldId = "856d3fd6-b982-4085-aa67-65d60bf41bb7";

    public TaskDto findMyTask(int whereToFindListId, int number) {
        return client.getTasksByCustomFields(
                List.of(12345678, 12345679, 12345610, 12345611, 12345123),
                BindFieldDto.of(
                        myNumberFieldId,
                        number
                )
        );
    }

}
```

Чтобы каждый раз не указывать **ListID** можно использовать `ListTaskClient`, который по дефолту ищет в трёх списках, но возвращает `List<TasksDto>`
(может вернуться несколько задач с одинаковым филдом, поэтому выбор нужной таски делегируется пользователю SDK), однако с опытом можно сказать: зачастую ни одной таски или больше одной таски значит конец света - FallFast.

`CreateTaskDto` и `UpdateTaskDto` слега отличаются, имеют общий синтаксис но в корне противоположны в поведении (примеры ниже) и вот в чём причина: 
мы можем создать таску и тут же обновить ей поля (все счастливы), но сделать такой же update нельзя, ClickUp проигнорирует все **CustomField**. Для этого приходится делать как **минимум два запроса**: один обновит таску, другой - покажет изменения. Меж тем каждый `CustomFieldRequest` будет обновляься отдельным запросом на отдельный эндпоинт, совершая N запросов. Итоговая сложность: **O(N+2)**, где **N - количество кастомных филдов**.

# Примеры использования ClickUp SDK:

## Получить таску по идентификатору:

```java
public class Service {

    private final TaskClient client;

    public TaskDto getTaskById(String id) {
        return client.getTaskById(id, false); // Второй аргумент позволяет выгрузить сабтаски
    }

}
```

## Получить таску по кастомному полю:

```java
public class Service {

    private final TaskClient client;

    public TaskDto getTaskByListIdAndEgarId(int listId, String egarId) {
        return client.getTasksByCustomField(
                listId,
                CustomFieldsRequest.builder()
                        .fieldId("836c9684-0c71-4714-aff2-900b0ded0685")
                        .operator(Operator.EQUALS.getOperator()) // Можно опустить, по дефолту EQUALS
                        .value(egarId)
                        .build()
        ).get();
    }
}
```

## Получить таску по кастомному полю, используя listid из properties файла:

```java
public class Service {

    private final ListTaskClient client; // ListTaskClient подтягивает listids из properties

    public TaskDto getTaskByEgarIdInMultipleLists(String egarId) {
        List<TasksDto> unchekedTasks = client.getTasksByCustomFields(CustomFieldRequest.builder()
                .fieldId("836c9684-0c71-4714-aff2-900b0ded0685")
                .value(egarId)
                .build()
        );
        // Если нужное поле повторяется в нескольких тасках, находящихся в разных листах, 
        // нужно корректно корректно это обработать (и иметь ввиду такую возможность).
        // В данном примере вернуться больше одной таски не должно, а потому - если таска
        // не одна - кидается исключение

        if (unchekedTasks.size() > 1) {
            throw new ApplicationException("Тасок больше чем одна");
        }

        return CollectionUtils.firstElement(unchekedTasks);
    }
}

```

## Создать таску:

```java
public class Service {

    private final TaskClient client;

    public TaskDto createProfile(int listId, String egarId, String firstname, String lastname, String onBoardId) {
        return client.createTask(
                listId,
                CreateTaskDto.ofName("Сотрудник: Иванов Иван Иванович")
                        .setDescription("Новый сотрудник, контракт до: 09.09.2023")
                        .setStatus(Status.OPEN) // Можно опустить, по дефолту Status.OPEN
                        .setCustomFields(
                                BindFieldDto.of("836d9684-0c72-4714-aff2-705b0ded0685", egarId),
                                BindFieldDto.of("391c9685-9c71-4198-afz1-891q3dlq0685", firstname),
                                BindFieldDto.of("012a4674-0f66-9901-qwr1-281q1sll0799", lastname),
                                BindFieldDto.of("628n1127-1q16-9501-opm2-012q7mgl9106", onBoardId)
                        )
        );
    }

}
```

## Привязать таску к пользователю:

```java

public class Service {

    private final TaskClient
            client;

    public TaskDto linkTaskTo(String taskId, String assignerId) {
        return client.updateTask(
                UpdateTaskDto.ofTaskId(taskId)
                        .assignTo(assignerId)
        );
    }

}

```

## Добавить новую таску в релейшншип таски:

```java

public class Service {

    private final TaskClient client;

    public TaskDto linkToTask(String taskId, String newTaskId) {
        return client.updateTask(
                UpdateTaskDto.ofTaskId(taskId)
                        .linkTask("628n1127-1q16-9501-opm2-012q7mgl9106", newTaskId)
        );
    }

}

```

## Обновить таску:

```java
import ru.egartech.sdk.dto.task.UpdateTaskDto;
import ru.egartech.sdk.dto.task.customfield.update.Assigner;

public class Service {

    private final TaskClient client;

    public TaskDto linkUnlinkSickday(String listId, String egarId, String sickdayId) {
        TaskDto user = client.getTasksByCustomFields(123,
                CustomFieldRequest
                        .create()
                        .setFieldId("836c9684-0c71-4714-aff2-900b0ded0685")
                        .setValue(egarId)
        ).getFirstTask(); // Получить пользователя по кастом айди

        BindFieldDto customField = BindFieldDto.of(
                "628n1127-1q16-9501-opm2-012q7mgl9106",
                TaskRelationship.create().link(sickdayId)
        ); // Привязать задачу к релейншипу 

        return client.updateTask(
                UpdateTaskDto.ofTaskId(user.getId())
                        .setAssignees(Assigner.of()
                                .link("2840171")
                        ) // Добавить главной задаче проверяющего
                        .setDescription("Сотрудник был обновлён! " + LocalDateTime.now()) // Обновить описание
                        .setCustomFields(customField) // Задать кастом филды
        );
    }

}
```