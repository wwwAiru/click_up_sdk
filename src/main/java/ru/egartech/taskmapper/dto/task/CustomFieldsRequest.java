package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.egartech.taskmapper.api.Operator;

@Data
@Builder
public class CustomFieldsRequest<T> {

    @JsonProperty("field_id")
    private String fieldId;

    @Builder.Default
    private String operator = Operator.EQUALS.getOperator();

    private T value;

}
