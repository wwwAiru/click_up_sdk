package ru.egartech.taskmapper.dto.task.customfield.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(staticName = "of")
public class Relationship {

    @JsonProperty("add")
    private List<String> toAdd = new ArrayList<>();
    @JsonProperty("rem")
    private List<String> toRemove = new ArrayList<>();

    public Relationship link(String... args) {
        toAdd = List.of(args);
        return this;
    }

    public Relationship unlink(String... args) {
        toRemove = List.of(args);
        return this;
    }
}
