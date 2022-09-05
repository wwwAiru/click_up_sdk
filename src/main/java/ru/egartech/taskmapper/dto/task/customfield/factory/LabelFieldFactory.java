package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelOptionDto;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelsFieldDto;

import java.util.List;


class LabelFieldFactory implements AbstractCustomFieldFactory<List<LabelOptionDto>> {
    @Override
    public CustomField<List<LabelOptionDto>> createField(String id, String name, List<LabelOptionDto> value) {
        return LabelsFieldDto.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }

    @Override
    public FieldType getType() {
        return FieldType.LABELS;
    }
}
