package ru.egartech.sdk.dto.task.deserialization.customfield.field.relationship;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RelationshipFieldDto extends CustomField<List<RelationshipValueDto>> {
    protected List<RelationshipValueDto> value = new ArrayList<>();
    @JsonProperty(value = "type_config")
    private RelationshipTypeConfig relationShipTypeConfig;
}
