package ru.egartech.sdk.dto.task.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.serialization.assigner.Assigner;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;
import ru.egartech.sdk.dto.task.serialization.customfield.update.TaskRelationship;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static java.util.Objects.isNull;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskDto extends RequestTaskDto {

    @NonNull
    private final String id;
    private String name;

    @JsonProperty("custom_id")
    private String customId;

    @JsonProperty("assignees")
    private Assigner assignees;

    @JsonProperty("custom_fields")
    private List<BindFieldDto> customFields;

    public abstract static class UpdateTaskDtoBuilder<C extends UpdateTaskDto, B extends UpdateTaskDtoBuilder<C, B>>
            extends RequestTaskDtoBuilder<C, B> {
        public B customFields() {
            if (isNull(this.customFields)) {
                this.customFields = new ArrayList<>();
            }
            return self();
        }

        public B customFields(BindFieldDto... customFields) {
            customFields();
            this.customFields.addAll(List.of(customFields));
            return self();
        }

        public B customFields(List<BindFieldDto> customFields) {
            customFields();
            this.customFields.addAll(customFields);
            return self();
        }

        public B linkTask(String relationshipId, String... taskId) {
            if (isNull(taskId)) {
                return self();
            }
            customFields(BindFieldDto.of(relationshipId, TaskRelationship.create().link(taskId)));
            return self();
        }

        public B unlinkTask(String relationshipId, String... taskId) {
            if (isNull(taskId)) {
                return self();
            }
            customFields(BindFieldDto.of(relationshipId, TaskRelationship.create().unlink(taskId)));
            return self();
        }

        public B assignTo(String... assignerId) {
            if (isNull(assignerId)) {
                return self();
            }
            this.assignees = (Assigner.of().link(assignerId));
            return self();
        }

        public B unassign(String... assignerId) {
            if (isNull(assignerId)) {
                return self();
            }
            this.assignees = (Assigner.of().unlink(assignerId));
            return self();
        }
    }
}
