package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.taskmapper.dto.task.customfield.update.Assigner;
import ru.egartech.taskmapper.dto.task.status.Status;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RequestTaskDto {

    @JsonProperty("text_content")
    private String textContent;
    private String description;
    private Status status;

    @JsonProperty("custom_fields")
    private List<BindFieldDto> customFields = new ArrayList<>();

    @JsonProperty("assignees")
    private Assigner assignees;

    public abstract String getId();

}
