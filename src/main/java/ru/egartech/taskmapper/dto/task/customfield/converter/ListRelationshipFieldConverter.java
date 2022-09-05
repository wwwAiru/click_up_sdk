package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.ListRelationShipDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class ListRelationshipFieldConverter implements FieldConverter<ListRelationShipDto> {

    private static final FieldType TYPE = FieldType.LIST_RELATIONSHIP;

    @Override
    public ListRelationShipDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));
        values.put("value", treeNode.get("type_config").get("subcategory_id")
                .toString()
                .replaceAll("\"", ""));

        return (ListRelationShipDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
