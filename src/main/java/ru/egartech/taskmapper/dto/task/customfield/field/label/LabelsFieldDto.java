package ru.egartech.taskmapper.dto.task.customfield.field.label;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(converter = LabelFieldConverter.class)
public class LabelsFieldDto extends AbstractField<List<LabelOptionDto>> {

    @JsonProperty(value = "type_config", access = JsonProperty.Access.WRITE_ONLY)
    private LabelTypeConfig labelTypeConfig;

}
