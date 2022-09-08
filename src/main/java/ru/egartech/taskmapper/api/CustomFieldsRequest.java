package ru.egartech.taskmapper.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomFieldsRequest<T> {

    @JsonProperty("field_id")
    private String fieldId;

    private String operator = "=";

    private T value;

}
