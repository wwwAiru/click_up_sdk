package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.empty.EmptyFieldDto;


class EmptyFieldFactory implements AbstractCustomFieldFactory<String> {
    @Override
    public CustomField<String> createField(String id, String name, String value) {
        return EmptyFieldDto.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }

    @Override
    public FieldType getType() {
        return FieldType.EMPTY;
    }
}
