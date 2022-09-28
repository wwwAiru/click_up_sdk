# ClickUp SDK

## Инструкция по подключению для среды разработки:

1. Склонировать/Обновить проект на своей машине
2. Прописать `mvn clean install` для импорта проекта в локальный репозиторий Maven
3. Прописать зависимость в pom.xml:

```
<dependency>
   <groupId>ru.egartech</groupId>
   <artifactId>click-up-sdk-spring-boot-starter</artifactId>
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
  # ID списков для поиска в ListTaskClient
  search-list:
    ids:
      - 12345678
      - 12345678
      - 12345678
```

# Краткое описание SDK

## О SDK

Задача (далее Таска) - главная сущность в ClickUp API. Этот SDK создан для работы с Тасками. Содержимое таски делится на
стандартное (`TaskDto`) и кастомные поля (`CustomField<?>`, далее кастом филды). Клиенты SDK реализуют API и дополняют
новыми методами (к примеру: updateTask, об этом ниже). Главные интерфейсы: `TaskClient` и `CustomFieldClient`. Далее
описания и примеры будут опираться на них (существует `ListTaskClient`, об этом ниже).

Для доступа в ClickUp нужен токен, но **каждый токен ограничен по числу запросов: 100 в минуту на каждый токен**.
Поэтому токены передаются в качестве списка, где, затем, каждый новый запрос будет обновлять токен в карусели, тем самым
не позволяя исчерпать лимит на каждый токен. Ошибки, связанные с исчерпанием лимита никак не обрабатываются,
предполагается что будут использоваться разные токены.

ClickUp API возвращает не очень приятные JSON объекты с огромной ветвистостью и разнообразием типов, потому - во
введении опускается десериализация, однако нужно уточнить: сущности, приходящие из ClickUp изменены для большего
удобства (**value всегда содержит полезную нагрузку**, остальные поля оставлены без изменений. Кастом филды хранятся по
формату **FieldID** + **CustomField**). Ещё одна проблема - ClickUp принимает не те же объекты, что отдаёт (даже если
оставить их без изменений) и в лучшем случае игнорирует, в худшем - отказывает в запросе, а это, при каскадном (об этом
ниже) обновлении, является фатальной ошибкой, уничтожающей данные. Для борьбы с этим на создание и обновление таски
используются разные кастом филды.

## О Тасках и Листах

Существует два (три) способа получить таску:

1. По **TaskID**
2. По **CustomField** (об этом ниже)
3. Без **CustomField** (`TaskClient.getTasksByCustomFields(int listId)`, об этом ниже)

**TaskID** каждой таски статичен, однако нигде не захардкожен: тасок много и они динамичны. Каждая таска хранится в
своём Списке (далее лист). При поиске по кастомным филдам мы должны знать лист (чтобы знать где искать таску). **
ListID** статичен, меняется только от среды к среде, обычно выносится в properties файл. Подводя итог, как мы ищем
таски:

* **ListID + CustomField** (нужно заранее знать ListID, занимает больше времени)
* **TaskID** (нужно заранее знать TaskID, самый быстрый)

## О Кастом филдах

Чаще всего мы не знаем **TaskID** перед запросом, поэтому - ищем по кастом филдам. Каждая таска имеет набор полей,
созданных для среды, у каждого поля есть идентификатор (**FieldID**, статичен), имя (**Name**, нестатичен) и технические
параметры. Получаемый кастом филд из кликапа десериализуется в удобный формат, доступ к каждому полю осуществляется с
помощью метода: `TaskDto.customField(String fieldID)`. Метод содержит **Generic** аргумент, поэтому приводить типы не
нужно.

Для запроса, как и в тасках, используется другой вид объектов, выглядящий так:

```json
{
  "field_id": "856d3fd6-b982-4085-aa67-65d60bf41bb7",
  "operator": "=",
  "value": 3
}
```

Операторов в ClickUp существует множество (полный список можно найти в документации ClickUp), но указанный выше самый
распространённый. Этот запрос чем-то похож на SQL, и выглядел бы так:

```sql
SELECT *
FROM Tasks
WHERE field_id = "856d3fd6-b982-4085-aa67-65d60bf41bb7", value = 3;
```

Воспроизводит такой JSON класс `BindFieldDto` (он имеет название Bind из-за того, что создавать кастомные филды нельзя,
можно только обновлять их значения и добавлять созданные вручную филды к таске). По дефолту оператор `=` поэтому
указывать его не нужно, достаточно указать **FieldId** и **Value**. Ниже приведён пример сервиса, который ищет все
таски, где кастом филд `Номер` с **FieldID** `856d3fd6-b982-4085-aa67-65d60bf41bb7` равен какому-либо числу (
предполагается что таска может быть в нескольких филдах, поэтому будет совершено несколько запросов):

```java
public class Service {

    private final TaskClient client;

    private final String myNumberFieldId = "856d3fd6-b982-4085-aa67-65d60bf41bb7";

    public TaskDto findMyTask(int number) {
        CustomFieldRequest<Integer> customFieldRequest = CustomFieldRequest.<String>builder()
                .fieldId(myNumberFieldId)
                .value(number)
                .build();

        return client.getTasksByCustomFields(
                List.of(12345678, 12345679, 12345610, 12345611, 12345123),
                customFieldRequest
        );
    }

}
```

Чтобы каждый раз не указывать **ListID** можно использовать `ListTaskClient`, который по дефолту ищет в трёх списках, но
возвращает `List<TasksDto>`
(может вернуться несколько задач с одинаковым филдом, поэтому выбор нужной таски делегируется пользователю SDK), однако
с опытом можно сказать: зачастую ни одной таски или больше одной таски значит конец света - FallFast.

`CreateTaskDto` и `UpdateTaskDto` слега отличаются, имеют общий синтаксис но в корне противоположны в поведении (примеры
ниже) и вот в чём причина:
мы можем создать таску и тут же обновить ей поля (все счастливы), но сделать такой же update нельзя, ClickUp
проигнорирует все **CustomField**. Для этого приходится делать как **минимум два запроса**: один обновит таску, другой -
покажет изменения. Меж тем каждый `CustomFieldRequest` будет обновляься отдельным запросом на отдельный эндпоинт,
совершая N запросов. Итоговая сложность: **O(N+2)**, где **N - количество кастомных филдов**.

Для извлечения конкретных кастом филдов из `TaskDto` используется `TaskDto.customField(String field_id)` (метод делает
неявный каст по объявленному типу переменной).

# Примеры использования ClickUp SDK:

## Получить таску по идентификатору:

```java
public class Service {
    private final TaskClient taskClient;

    public TaskDto getTaskById(String id) {
        return taskClient.getTaskById(id, false); // Второй аргумент позволяет выгрузить сабтаски
    }
}
```

## Получить таску по кастомному полю:

```java
public class Service {
    private final TaskClient taskClient;

    public TaskDto getTaskByListIdAndEgarId(int listId, String egarId) {
        CustomFieldRequest<String> customFieldRequest = CustomFieldRequest.<String>builder()
                .fieldId("836c9684-0c71-4714-aff2-900b0ded0685")
                .operator(Operator.EQUALS.getOperator()) // Можно опустить, по дефолту EQUALS
                .value(egarId)
                .build();
        return taskClient.getTasksByCustomField(listId, customFieldRequest).get();
    }
}
```

## Получить таску по кастомному полю, используя listid из properties файла:

```java
public class Service {
    // ListTaskClient подтягивает listids из properties
    private final ListTaskClient taskClient;

    public TaskDto getTaskByEgarIdInMultipleLists(String egarId) {
        CustomFieldRequest<String> customFieldRequest = CustomFieldRequest.<String>builder()
                .fieldId("836c9684-0c71-4714-aff2-900b0ded0685")
                .value(egarId)
                .build();

        List<TasksDto> uncheckedTasks = taskClient.getTasksByCustomFields(customFieldRequest);

        // Если нужное поле повторяется в нескольких тасках, находящихся в разных листах, 
        // нужно корректно корректно это обработать (и иметь ввиду такую возможность).
        // В данном примере вернуться больше одной таски не должно, а потому - если таска
        // не одна - кидается исключение
        if (unchekedTasks.size() > 1) {
            throw new ApplicationException("Тасок больше чем одна");
        }
        return CollectionUtils.firstElement(uncheckedTasks).getFirstTask();
    }
}

```

## Создать таску:

```java
public class Service {
    private final TaskClient taskClient;

    public TaskDto createProfile(int listId, String egarId, String firstname, String lastname, String onBoardId) {
        CreateTaskDto createTaskDto = CreateTaskDto.builder()
                .name("Сотрудник: Иванов Иван Иванович")
                .description("Новый сотрудник, контракт до: 09.09.2023")
                .status("новый")
                .build();

        return client.createTask(listId, createTaskDto);
    }
}
```

## Привязать таску к пользователю:

```java
public class Service {
    private final TaskClient taskClient;

    public TaskDto linkTaskTo(String taskId, String assignerId) {
        UpdateTaskDto updateTaskDto = UpdateTaskDto.builder()
                .id("task_id")
                .assignes(Assigner.link("assigner_id"))
                .build();

        return taskClient.updateTask(updateTaskDto);
    }
}

```

## Добавить новую таску в релейшншип таски:

```java
public class Service {
    private final TaskClient taskClient;

    public TaskDto linkToTask(String taskId, String newTaskId) {
        UpdateTaskDto updateTaskDto = UpdateTaskDto.builder()
                .id("task_id")
                .customFields(List.of(
                        BindFieldDto.linkTask("to_relationship_field_id", "task_id_1", "task_id_2")
                ))
                .build();

        return taskClient.updateTask(updateTaskDto);
    }
}

```

## Обновить таску:

```java
import ru.egartech.sdk.dto.task.serialization.assigner.Assigner;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;

public class Service {
    private final TaskClient taskClient;

    public TaskDto linkUnlinkSickDay(String listId, String egarId, String sickDayId) {
        CustomFieldRequest<String> customFieldRequest = CustomFieldRequest.<String>builder()
                .fieldId("836c9684-0c71-4714-aff2-900b0ded0685")
                .value(egarId)
                .build();

        // Получить пользователя по кастом айди
        TaskDto user = taskClient.getTasksByCustomFields(123, customFieldRequest).getFirstTask();

        List<BindFieldDto> customFields = List.of(
                // добавить кастомное поле
                BindFieldDto.of("field_id", LocalDate.now()),
                BindFieldDto.linkTask("to_relationship_field_id", "task_id_1", "task_id_2")
        );

        UpdateTaskDto updateTaskDto = UpdateTaskDto.builder()
                .description("Сотрудник был обновлён! " + LocalDateTime.now())
                // ids профилей пользователей, которые утверждаются задачи
                .assignes(Assigner.link("assigner_id_1", "assigner_id_2"))
                .customFields(customField)
                .build();

        return taskClient.updateTask(updateTaskDto);
    }
}
```