package ru.egartech.taskmapper.api;

import ru.egartech.taskmapper.dto.task.CreateTaskDto;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.UpdateTaskDto;

import java.util.List;

public interface TaskClient {

    TaskDto getTaskById(String id, Boolean hasIncludeSubtasks);

    <T> List<TaskDto> getTasksByCustomField(String listId, CustomFieldsRequest<T>... customFieldsRequest);

    TaskDto createTask(CreateTaskDto createTaskDto);

    TaskDto updateTask(UpdateTaskDto updateTaskDto);

}
