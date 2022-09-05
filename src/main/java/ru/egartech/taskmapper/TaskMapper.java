package ru.egartech.taskmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.TasksDto;

import java.util.List;


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
