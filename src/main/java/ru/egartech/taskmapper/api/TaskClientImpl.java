package ru.egartech.taskmapper.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egartech.taskmapper.dto.task.CreateTaskDto;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.TasksDto;
import ru.egartech.taskmapper.dto.task.UpdateTaskDto;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskClientImpl implements TaskClient {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    @Override
    public TaskDto getTaskById(String id, Boolean hasIncludeSubtasks) {
        Map<String, Object> uriVariables = new HashMap<>();

        uriVariables.put("id", id);
        uriVariables.put("include_subtasks", hasIncludeSubtasks);

        return restTemplate.getForObject(
                UrlProvider.SEARCH_TASK_BY_ID_URL.getUrl(),
                TaskDto.class,
                uriVariables);
    }

    @SafeVarargs
    @SneakyThrows
    @Override
    public final <T> TasksDto getTasksByCustomField(String listId, CustomFieldsRequest<T>... customFieldsRequest) {
        Map<String, Object> uriVariables = new HashMap<>();

        uriVariables.put("list_id", listId);
        uriVariables.put("custom_field_req", mapper.writeValueAsString(customFieldsRequest));

        return restTemplate.getForObject(
                UrlProvider.SEARCH_TASKS_BY_CUSTOM_FIELDS_URL.getUrl(),
                TasksDto.class,
                uriVariables);
    }

    @Override
    public TaskDto createTask(CreateTaskDto createTaskDto) {
        return null;
    }

    @Override
    public TaskDto updateTask(UpdateTaskDto updateTaskDto) {
        return null;
    }

}
