package ru.egartech.sdk.dto.task.serialization;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateTaskDto extends RequestTaskDto {
    @NonNull
    private final String name;
    @Builder.Default
    private String id = "ignored";
}
