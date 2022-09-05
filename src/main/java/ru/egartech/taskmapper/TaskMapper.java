package ru.egartech.taskmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.TaskDto;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ObjectMapper mapper;

    @SneakyThrows
    public TaskDto map(String json) {
        return mapper.readValue(json, TaskDto.class);
    }

}
