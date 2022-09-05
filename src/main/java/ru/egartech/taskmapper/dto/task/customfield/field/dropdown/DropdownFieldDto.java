package ru.egartech.taskmapper.dto.task.customfield.field.dropdown;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DropdownFieldDto extends AbstractField<DropdownOption> {
    @Override
    public FieldType getType() {
        return FieldType.DROP_DOWN;
    }
}
