package ru.egartech.sdk.dto.task.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RequestTaskDto {
    @JsonProperty("text_content")
    private String textContent;

    private String description;

    private String status;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("start_date")
    private String startDate;

    public abstract String getId();

    public abstract String getName();
}
