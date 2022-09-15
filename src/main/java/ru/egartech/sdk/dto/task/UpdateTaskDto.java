package ru.egartech.sdk.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import ru.egartech.sdk.dto.task.customfield.update.Assigner;
import ru.egartech.sdk.dto.task.customfield.update.TaskRelationship;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static java.util.Objects.isNull;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "ofTaskId")
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskDto extends RequestTaskDto {

    private final String id;

    @JsonProperty("custom_id")
    private String customId;

    @JsonProperty("assignees")
    private Assigner assignees;

    public UpdateTaskDto linkTask(String relationshipId, String... taskId) {
        if (isNull(taskId)) {
            return this;
        }
        bindCustomFields(BindFieldDto.of(
                relationshipId,
                TaskRelationship.create().link(taskId))
        );
        return this;
    }

    public UpdateTaskDto unlinkTask(String relationshipId, String... taskId) {
        if (isNull(taskId)) {
            return this;
        }
        bindCustomFields(BindFieldDto.of(
                relationshipId,
                TaskRelationship.create().unlink(taskId))
        );
        return this;
    }

    public UpdateTaskDto assignTo(String... assignerId) {
        if (isNull(assignerId)) {
            return this;
        }
        setAssignees(Assigner.of().link(assignerId));
        return this;
    }

    public UpdateTaskDto unassign(String... assignerId) {
        if (isNull(assignerId)) {
            return this;
        }
        setAssignees(Assigner.of().unlink(assignerId));
        return this;
    }

}
