package ru.egartech.taskmapper.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomFieldsRequest<T> {

    private String fieldId;

    private String operator = "=";

    private T value;

}
