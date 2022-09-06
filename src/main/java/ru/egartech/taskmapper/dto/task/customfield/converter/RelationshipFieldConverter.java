package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipDto;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipTypeConfig;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipValueDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RelationshipFieldConverter implements FieldConverter<RelationShipDto> {

    private static final FieldType TYPE = FieldType.LIST_RELATIONSHIP;

    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public RelationShipDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));

        RelationShipTypeConfig relationShipTypeConfig = mapper.readValue(treeNode.get("type_config").traverse(), RelationShipTypeConfig.class);
        List<RelationShipValueDto> relationShipValueDtos = mapper.readValue(treeNode.get("value").traverse(), new TypeReference<>() {
        });

        relationShipValueDtos.forEach(e -> e.setSubcategoryId(relationShipTypeConfig.getSubcategoryId()));

        values.put("value", relationShipValueDtos);

        return (RelationShipDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
