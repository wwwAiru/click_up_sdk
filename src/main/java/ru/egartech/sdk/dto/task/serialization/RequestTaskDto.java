package ru.egartech.sdk.dto.task.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RequestTaskDto {

    @JsonProperty("text_content")
    private String textContent;
    private String description;
    private String status;

    public abstract String getId();
    public abstract String getName();
}
