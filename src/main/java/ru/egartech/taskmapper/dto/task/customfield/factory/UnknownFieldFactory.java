package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.unknown.UnknownFieldDto;


class UnknownFieldFactory implements AbstractCustomFieldFactory<String> {
    @Override
    public CustomField<String> createField(String id, String name, String value) {
        return UnknownFieldDto.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }

    @Override
    public FieldType getType() {
        return FieldType.UNKNOWN;
    }
}
