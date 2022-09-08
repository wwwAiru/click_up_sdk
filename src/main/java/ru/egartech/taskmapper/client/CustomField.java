package ru.egartech.taskmapper.client;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomField {

    private String fieldId;
    private String operator;
    private String value;

    public CustomField setOperator(Operator operator) {
        this.operator = operator.getOperator();
        return this;
    }
}
