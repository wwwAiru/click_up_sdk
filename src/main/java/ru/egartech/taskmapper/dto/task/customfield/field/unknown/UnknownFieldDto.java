package ru.egartech.taskmapper.dto.task.customfield.field.unknown;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UnknownFieldDto extends AbstractField<String> {
    @Override
    public FieldType getType() {
        return FieldType.UNKNOWN;
    }
}
