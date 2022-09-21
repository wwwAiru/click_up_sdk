package ru.egartech.sdk.dto.task.serialization.assigner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class Assigner {

    @JsonProperty("add")
    @Singular("add")
    private List<String> toAdd;

    @JsonProperty("rem")
    @Singular("remove")
    private List<String> toRemove;

    public static Assigner link(@NonNull String... args) {
        return Assigner.builder()
                .toAdd(List.of(args))
                .build();
    }

    public static Assigner unlink(@NonNull String... args) {
        return Assigner.builder()
                .toRemove(List.of(args))
                .build();
    }

}
