package ru.egartech.sdk.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import ru.egartech.sdk.api.Operator;

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

}
