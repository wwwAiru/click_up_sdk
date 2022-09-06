package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.taskmapper.dto.task.assigner.AssignerDto;
import ru.egartech.taskmapper.dto.task.creator.Creator;
import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.status.Status;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RequestTaskDto {

    @JsonProperty("text_content")
    private String textContent;

    private String description;
    private Status status;

    @JsonProperty("orderindex")
    private int orderIndex;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("date_updated")
    private String dateUpdated;

    @JsonProperty("date_closed")
    private String dateClosed = null;

    private Creator creator;
    private AssignerDto[] assigners = {};
    private Object[] checklists = {};
    private Object[] tags = {};
    private Object parent;
    private Object priority;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("time_estimate")
    private String timeEstimate;

    @JsonProperty("time_spent")
    private String timeSpent;

    @JsonProperty("custom_fields")
    private List<CustomField<?>> customFields;

    private String url;

}
