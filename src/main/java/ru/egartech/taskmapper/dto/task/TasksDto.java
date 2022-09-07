package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.taskmapper.exception.customfield.CustomFieldNotFoundException;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TasksDto {

    @JsonProperty("tasks")
    private List<TaskDto> tasks;

    @JsonIgnore
    public TaskDto get() {
        return tasks.stream()
                .findFirst()
                .orElseThrow(
                        () -> new CustomFieldNotFoundException("tasks")
                );
    }

}
