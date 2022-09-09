package ru.egartech.taskmapper.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egartech.taskmapper.dto.task.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskClientImpl implements TaskClient {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    @Override
    public TaskDto getTaskById(String id, Boolean includeSubtasks) {
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
    public final TasksDto getTasksByCustomFields(int listId, CustomFieldRequest<?>... customFieldRequest) {
        Map<String, Object> uriVariables = new HashMap<>();

        uriVariables.put("list_id", listId);
        uriVariables.put("custom_field_req", mapper.writeValueAsString(customFieldRequest));

        return restTemplate.getForObject(
                UrlProvider.SEARCH_TASKS_BY_CUSTOM_FIELDS_URL.getUrl(),
                TasksDto.class,
                uriVariables);
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
    public TaskDto updateTask(RequestTaskDto task) {
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
