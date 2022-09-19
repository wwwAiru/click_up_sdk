package ru.egartech.sdk.dto.task.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import ru.egartech.sdk.dto.task.serialization.assigner.Assigner;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;
import ru.egartech.sdk.dto.task.serialization.customfield.update.TaskRelationship;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static java.util.Objects.isNull;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "ofTaskId")
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskDto extends RequestTaskDto {

    private final String id;
    private String name;

    @JsonProperty("custom_id")
    private String customId;

    @JsonProperty("assignees")
    private Assigner assignees;

    @JsonProperty("custom_fields")
    private List<BindFieldDto> customFields = new ArrayList<>();

    public RequestTaskDto bindCustomFields(BindFieldDto... customFields) {
        this.customFields.addAll(List.of(customFields));
        return this;
    }

    public RequestTaskDto bindCustomFields(List<BindFieldDto> customFields) {
        this.customFields.addAll(customFields);
        return this;
    }

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
