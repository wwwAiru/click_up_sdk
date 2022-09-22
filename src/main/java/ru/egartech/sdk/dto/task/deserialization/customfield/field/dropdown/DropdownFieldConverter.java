package ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown;

import com.fasterxml.jackson.databind.util.StdConverter;

import static java.util.Objects.isNull;

public class DropdownFieldConverter extends StdConverter<DropdownFieldDto, DropdownFieldDto> {
    @Override
    public DropdownFieldDto convert(DropdownFieldDto dfd) {
        DropdownTypeConfig dropdownTypeConfig = dfd.getDropdownTypeConfig();
        DropdownOption dropdownOption = dfd.getValue();
        if (isNull(dropdownOption)) {
            return dfd;
        }
        Integer orderindex = dropdownOption.getValue();
        dfd.setValue(dropdownTypeConfig.byOrderIndex(orderindex));
        return dfd;
    }
}
