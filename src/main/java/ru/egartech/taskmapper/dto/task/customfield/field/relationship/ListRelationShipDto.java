package ru.egartech.taskmapper.dto.task.customfield.field.relationship;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ListRelationShipDto extends AbstractField<String> {
    @Override
    public FieldType getType() {
        return FieldType.LIST_RELATIONSHIP;
    }
}
