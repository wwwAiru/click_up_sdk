package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.taskmapper.exception.ApplicationException;
import ru.egartech.taskmapper.exception.customfield.CustomFieldNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TasksDto {

    @JsonProperty("tasks")
    private List<TaskDto> tasks = new ArrayList<>();

    @JsonIgnore
    public TaskDto getFirstTask() {

        if (tasks.size() > 1) {
            throw new ApplicationException("Задач больше чем одна");
        }

        return tasks.stream()
                .findFirst()
                .orElseThrow(
                        () -> new CustomFieldNotFoundException("tasks")
                );
    }

}
