package ru.egartech.taskmapper.dto.task.customfield.field.label;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.stream.Collectors;

public class LabelFieldConverter extends StdConverter<LabelsFieldDto, LabelsFieldDto> {
    @Override
    public LabelsFieldDto convert(LabelsFieldDto value) {
        LabelTypeConfig labelTypeConfig = value.getLabelTypeConfig();
        if (value.getValue() == null) {
            return value;
        }

        value.setValue(value.getValue().stream()
                .map(e -> labelTypeConfig.byLabelId(e.getId()))
                .collect(Collectors.toList()));

        return value;
    }
}
