package ru.egartech.taskmapper.dto.task.customfield.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(staticName = "create")
public class TaskRelationship {

    @JsonProperty("add")
    private List<String> toAdd = new ArrayList<>();
    @JsonProperty("rem")
    private List<String> toRemove = new ArrayList<>();

    public TaskRelationship link(String... taskIds) {
        toAdd = List.of(taskIds);
        return this;
    }

    public TaskRelationship unlink(String... taskIds) {
        toRemove = List.of(taskIds);
        return this;
    }
}
