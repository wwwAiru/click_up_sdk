package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.date.DateFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.util.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DateFieldConverter implements FieldConverter<DateFieldDto> {
    private static final FieldType TYPE = FieldType.DATE;

    @Override
    public DateFieldDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));

        String milliseconds = treeNode.get("value").toString().replaceAll("\"", "");

        values.put("value", milliseconds);


        return (DateFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
