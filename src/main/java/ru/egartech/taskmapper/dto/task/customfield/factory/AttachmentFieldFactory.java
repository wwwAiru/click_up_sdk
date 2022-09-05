package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentDto;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentFieldDto;

import java.util.List;


class AttachmentFieldFactory implements AbstractCustomFieldFactory<List<AttachmentDto>> {
    @Override
    public CustomField<List<AttachmentDto>> createField(String id, String name, List<AttachmentDto> value) {
        return AttachmentFieldDto.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }

    @Override
    public FieldType getType() {
        return FieldType.ATTACHMENT;
    }
}
