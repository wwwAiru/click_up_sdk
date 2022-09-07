package ru.egartech.taskmapper.dto.task.customfield.field.relationship;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.List;

public class RelationShipFieldConverter extends StdConverter<RelationShipFieldDto, RelationShipFieldDto> {
    @Override
    public RelationShipFieldDto convert(RelationShipFieldDto value) {
        RelationShipTypeConfig relationShipTypeConfig = value.getRelationShipTypeConfig();
        if (relationShipTypeConfig == null) {
            return value;
        }

        String subcategoryId = relationShipTypeConfig.getSubcategoryId();
        if (subcategoryId == null) {
            return value;
        }

        List<RelationShipValueDto> values = value.getValue();
        if (values == null) {
            return value;
        }

        values.forEach(e -> e.setSubcategoryId(subcategoryId));

        return value;
    }
}
