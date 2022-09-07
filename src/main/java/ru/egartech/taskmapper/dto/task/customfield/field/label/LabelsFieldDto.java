package ru.egartech.taskmapper.dto.task.customfield.field.label;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelsFieldDto extends AbstractField<List<LabelOptionDto>> {

    @Override
    public FieldType getType() {
        return FieldType.LABELS;
    }

}
