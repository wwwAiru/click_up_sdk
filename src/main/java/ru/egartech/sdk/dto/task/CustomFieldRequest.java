package ru.egartech.sdk.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(staticName = "create")
@Accessors(chain = true)
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
