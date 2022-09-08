package ru.egartech.taskmapper.client;

import ru.egartech.taskmapper.dto.task.CreateTaskDto;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.UpdateTaskDto;

import java.util.List;

public interface ClickUpClient {

    TaskDto createTask(CreateTaskDto createTaskDto);

    TaskDto updateTask(UpdateTaskDto updateTaskDto);

    void deleteTask(String id);

    List<TaskDto> getTasks(int listId, CustomField... customFields);

    List<TaskDto> getTasks(int listId, boolean includeSubtasks, int page, CustomField... customFields);

    TaskDto getTask(String taskId);

    TaskDto getTask(String taskId, boolean includeSubtasks);

}
