package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.url.UrlFieldDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class UrlFieldConverter implements FieldConverter<UrlFieldDto> {
    private static final FieldType TYPE = FieldType.URL;

    @Override
    public UrlFieldDto convert(TreeNode treeNode) {
        Map<String, String> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));
        values.put("value", treeNode.get("value").toString().replaceAll("\"", ""));

        return (UrlFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
