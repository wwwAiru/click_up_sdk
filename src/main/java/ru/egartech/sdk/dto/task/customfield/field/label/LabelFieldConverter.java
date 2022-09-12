package ru.egartech.sdk.dto.task.customfield.field.label;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class LabelFieldConverter extends StdConverter<LabelsFieldDto, LabelsFieldDto> {
    @Override
    public LabelsFieldDto convert(LabelsFieldDto lfd) {
        LabelTypeConfig labelTypeConfig = lfd.getLabelTypeConfig();
        if (!nonNull(lfd.getValue())) {
            return lfd;
        }
        lfd.setValue(lfd.getValue().stream()
                .map(e -> labelTypeConfig.byLabelId(e.getId()))
                .collect(Collectors.toList()));
        return lfd;
    }
}
