package ru.egartech.taskmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.TasksDto;


@RequiredArgsConstructor
public class TaskMapper {

    private final ObjectMapper mapper;

    @SneakyThrows
    public TaskDto map(String json) {
        return mapper.readValue(json, TaskDto.class);
    }

    @SneakyThrows
    public TasksDto mapList(String json) {
        return mapper.readValue(json, TasksDto.class);
    }

}
