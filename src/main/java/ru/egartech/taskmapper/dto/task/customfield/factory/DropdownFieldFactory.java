package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownOption;


class DropdownFieldFactory implements AbstractCustomFieldFactory<DropdownOption> {
    @Override
    public CustomField<DropdownOption> createField(String id, String name, DropdownOption value) {
        return DropdownFieldDto.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }

    @Override
    public FieldType getType() {
        return FieldType.DROP_DOWN;
    }
}
