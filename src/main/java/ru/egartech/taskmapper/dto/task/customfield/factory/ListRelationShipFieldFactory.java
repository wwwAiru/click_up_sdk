package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipDto;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipValueDto;

import java.util.List;


class ListRelationShipFieldFactory implements AbstractCustomFieldFactory<List<RelationShipValueDto>> {
    @Override
    public CustomField<List<RelationShipValueDto>> createField(String id, String name, List<RelationShipValueDto> value) {
        return RelationShipDto.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }

    @Override
    public FieldType getType() {
        return FieldType.LIST_RELATIONSHIP;
    }
}
