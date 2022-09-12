# ClickUp SDK

## Инструкция по подключению для среды разработки: 

1. Склонировать/Обновить проект на своей машине 
2. Прописать `mvn clean install` для импорта проекта в локальный репозиторий Maven
3. Прописать зависимость в pom.xml:
```
<dependency>
   <groupId>ru.egartech</groupId>
   <artifactId>click-up-sdk</artifactId>
   <version>0.0.1-SNAPSHOT</version>
</dependency>
```
4. Прописать аннотацию `@ComponentScan(basePackages = "ru.egartech.*")` над `Application.class`

5. Создать `@Bean` `RestTemplate` и добавить туда `AuthorizationRequestInterceptor`:

```java
public class Config {

    @Bean
    public RestTemplate restTemplate(
            @Autowired
            AuthorizationRequestInterceptor interceptor
    ) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(interceptor));
        return restTemplate;
    }
} 
```

7. application.yml:

```yaml
clickup:
  api:
    token: token
```

## Примеры использования ClickUp SDK:

Получить таску по идентификатору:

```java
public class Service {

    private final TaskClient client;

    public TaskDto getTaskById(String id) {
        return client.getTaskById(id, false); // Второй аргумент позволяет выгрузить сабтаски
    }

}
```

Получить таску по кастомному полю:

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

Создать таску:

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

Привязать таску к пользователю:

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

Добавить новую таску в релейшншип таски:

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

Обновить таску:

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