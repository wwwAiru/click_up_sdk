package ru.egartech.sdk.dto.task.serialization.customfield.update;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor(staticName = "of")
public class BindFieldDto {

    private final String fieldId;
    private final Object value;

    public static BindFieldDto linkTask(@NonNull String relationshipId, @NonNull String... taskId) {
        return BindFieldDto.builder()
                .fieldId(relationshipId)
                .value(TaskRelationship.create().link(taskId))
                .build();
    }

    public static BindFieldDto unlinkTask(@NonNull String relationshipId, @NonNull String... taskId) {
        return BindFieldDto.builder()
                .fieldId(relationshipId)
                .value(TaskRelationship.create().unlink(taskId))
                .build();
    }
}
