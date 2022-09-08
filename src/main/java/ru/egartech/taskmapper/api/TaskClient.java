package ru.egartech.taskmapper.api;

import ru.egartech.taskmapper.dto.task.CreateTaskDto;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.TasksDto;
import ru.egartech.taskmapper.dto.task.UpdateTaskDto;

public interface TaskClient {

    TaskDto getTaskById(String id, Boolean hasIncludeSubtasks);

    <T> TasksDto getTasksByCustomField(String listId, CustomFieldsRequest<T>... customFieldsRequest);

    TaskDto createTask(CreateTaskDto createTaskDto);

    TaskDto updateTask(UpdateTaskDto updateTaskDto);

}
