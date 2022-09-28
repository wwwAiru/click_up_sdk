package ru.egartech.sdk.dto.task.serialization.customfield.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomFieldRequest<T> {
    @JsonProperty("field_id")
    private String fieldId;
    @Builder.Default
    private String operator = Operator.EQUALS.getOperator();
    private T value;

    @RequiredArgsConstructor
    public enum Operator {
        EQUALS("=");

        @Getter
        private final String operator;
    }
}
