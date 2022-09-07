package ru.egartech.taskmapper.dto.task.customfield.field.relationship;

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
@JsonDeserialize(converter = RelationShipFieldConverter.class)
public class RelationShipFieldDto extends AbstractField<List<RelationShipValueDto>> {

    @JsonProperty(value = "type_config", access = JsonProperty.Access.WRITE_ONLY)
    private RelationShipTypeConfig relationShipTypeConfig;

}
