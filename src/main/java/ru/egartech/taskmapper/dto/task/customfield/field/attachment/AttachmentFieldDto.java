package ru.egartech.taskmapper.dto.task.customfield.field.attachment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AttachmentFieldDto extends AbstractField<List<AttachmentDto>> {
    @Override
    public FieldType getType() {
        return FieldType.ATTACHMENT;
    }
}
