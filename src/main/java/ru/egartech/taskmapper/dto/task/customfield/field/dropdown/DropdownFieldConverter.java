package ru.egartech.taskmapper.dto.task.customfield.field.dropdown;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DropdownFieldConverter extends StdConverter<DropdownFieldDto, DropdownFieldDto> {
    @Override
    public DropdownFieldDto convert(DropdownFieldDto value) {
        DropdownTypeConfig dropdownTypeConfig = value.getDropdownTypeConfig();
        DropdownOption dropdownOption = value.getValue();

        if (dropdownOption == null) {
            return value;
        }

        Integer orderindex = dropdownOption.getValue();

        if (orderindex == null) {
            return value;
        }

        value.setValue(dropdownTypeConfig.byOrderIndex(orderindex));

        return value;
    }
}
