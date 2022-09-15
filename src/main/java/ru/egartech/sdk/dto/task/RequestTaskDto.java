package ru.egartech.sdk.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RequestTaskDto {

    @JsonProperty("text_content")
    private String textContent;
    private String description;
    private String status;

    @JsonProperty("custom_fields")
    private List<BindFieldDto> customFields = new ArrayList<>();

    public abstract String getId();
    public abstract String getName();

    public RequestTaskDto bindCustomFields(BindFieldDto... customFields) {
        this.customFields.addAll(List.of(customFields));
        return this;
    }

    public RequestTaskDto bindCustomFields(List<BindFieldDto> customFields) {
        this.customFields.addAll(customFields);
        return this;
    }
}
