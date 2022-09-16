package ru.egartech.sdk.dto.task.serialization.assigner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(staticName = "of")
public class Assigner {

    @JsonProperty("add")
    private List<String> toAdd = new ArrayList<>();
    @JsonProperty("rem")
    private List<String> toRemove = new ArrayList<>();

    public Assigner link(String... args) {
        toAdd = List.of(args);
        return this;
    }

    public Assigner unlink(String... args) {
        toRemove = List.of(args);
        return this;
    }

}
