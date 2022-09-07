package ru.egartech.taskmapper.dto.task.customfield.field.relationship;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RelationShipFieldDto extends CustomField<List<RelationShipValueDto>> {

    @JsonProperty(value = "type_config")
    private RelationShipTypeConfig relationShipTypeConfig;

}
