package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        return tasks.stream()
                .findFirst()
                .orElseThrow(
                        () -> new CustomFieldNotFoundException("tasks")
                );
    }

}
