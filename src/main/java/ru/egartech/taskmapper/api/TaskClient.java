package ru.egartech.taskmapper.api;

import ru.egartech.taskmapper.dto.task.*;

public interface TaskClient {

    TaskDto getTaskById(String id, Boolean hasIncludeSubtasks);

    <T> TasksDto getTasksByCustomField(String listId, CustomFieldsRequest<T>... customFieldsRequest);

    TaskDto createTask(int listId, RequestTaskDto createTaskDto);

    TaskDto updateTask(RequestTaskDto updateTaskDto);

}
