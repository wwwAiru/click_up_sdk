package ru.egartech.sdk.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.serialization.RequestTaskDto;
import ru.egartech.sdk.dto.task.serialization.UpdateTaskDto;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;
import ru.egartech.sdk.property.UrlProvider;

import java.util.*;

@Component
@Primary
@RequiredArgsConstructor
public class TaskClientImpl implements TaskClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Override
    public TaskDto getTaskById(String id,
                               Boolean includeSubtasks) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        uriVariables.put("include_subtasks", includeSubtasks);
        return restTemplate.getForObject(
                UrlProvider.SEARCH_TASK_BY_ID_URL.getUrl(),
                TaskDto.class,
                uriVariables);
    }

    @SneakyThrows
    @Override
    public TasksDto getTasksByCustomFields(int listId,
                                           Boolean includeSubtasks,
                                           CustomFieldRequest<?>... customFieldRequest) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("list_id", listId);
        uriVariables.put("include_subtasks", includeSubtasks);
        uriVariables.put("custom_field_req", mapper.writeValueAsString(customFieldRequest));
        return restTemplate.getForObject(
                UrlProvider.SEARCH_TASKS_BY_CUSTOM_FIELDS_URL.getUrl(),
                TasksDto.class,
                uriVariables);
    }

    @Override
    public List<TasksDto> getTasksByCustomFields(Collection<Integer> lists,
                                                 Boolean includeSubtasks,
                                                 CustomFieldRequest<?>... customFieldRequest) {
        return lists
                .stream()
                .filter(Objects::nonNull)
                .map(id -> getTasksByCustomFields(id, includeSubtasks, customFieldRequest))
                .filter(td -> !td.getTasks().isEmpty())
                .toList();
    }

    @Override
    public TaskDto createTask(int listId, RequestTaskDto createTaskDto) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("list_id", listId);
        return restTemplate.postForObject(
                UrlProvider.CREATE_TASK.getUrl(),
                createTaskDto,
                TaskDto.class,
                uriVariables
        );
    }

    @Override
    public TaskDto updateTask(UpdateTaskDto task) {
        updateTaskWithoutCustomFields(task);
        updateAllCustomFieldsDto(task.getId(), task.getCustomFields());
        return getTaskById(task.getId(), false);
    }

    private void updateTaskWithoutCustomFields(RequestTaskDto dto) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", dto.getId());
        restTemplate.put(
                UrlProvider.UPDATE_TASK.getUrl(),
                dto,
                uriVariables
        );
    }

    private void updateAllCustomFieldsDto(String taskId, List<BindFieldDto> fields) {
        fields.forEach(field -> updateCustomField(taskId, field));
    }

    private void updateCustomField(String taskId, BindFieldDto field) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", taskId);
        uriVariables.put("field_id", field.getFieldId());
        restTemplate.postForObject(
                UrlProvider.UPDATE_CUSTOM_FIELD.getUrl(),
                field,
                Object.class,
                uriVariables
        );
    }
}