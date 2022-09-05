package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.number.NumberFieldDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class NumberFieldConverter implements FieldConverter<NumberFieldDto> {
    private static final FieldType TYPE = FieldType.NUMBER;

    @Override
    public NumberFieldDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));
        values.put("value", Long.parseLong(treeNode.get("value").toString()));

        return (NumberFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
