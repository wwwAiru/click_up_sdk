package ru.egartech.taskmapper.dto.task.customfield.field.label;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class LabelFieldConverter extends StdConverter<LabelsFieldDto, LabelsFieldDto> {
    @Override
    public LabelsFieldDto convert(LabelsFieldDto lfd) {
        LabelTypeConfig labelTypeConfig = lfd.getLabelTypeConfig();
        if (isNull(lfd.getValue())) {
            return lfd;
        }
        lfd.setValue(lfd.getValue().stream()
                .map(e -> labelTypeConfig.byLabelId(e.getId()))
                .collect(Collectors.toList()));
        return lfd;
    }
}
