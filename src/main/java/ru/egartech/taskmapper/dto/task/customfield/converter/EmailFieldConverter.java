package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.email.EmailFieldDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailFieldConverter implements FieldConverter<EmailFieldDto> {
    private static final FieldType TYPE = FieldType.EMAIL;

    @Override
    public EmailFieldDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));
        values.put("value", treeNode.get("value").toString().replaceAll("\"", ""));

        return (EmailFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
