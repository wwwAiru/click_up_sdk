package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownTypeConfig;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DropdownFieldConverter implements FieldConverter<DropdownFieldDto> {
    private static final FieldType TYPE = FieldType.DROP_DOWN;

    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public DropdownFieldDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));

        DropdownTypeConfig typeConfig = mapper.readValue(treeNode.get("type_config").traverse(), DropdownTypeConfig.class);
        int index = mapper.readValue(treeNode.get("value").traverse(), Integer.class);

        values.put("value", typeConfig.byOrderIndex(index));

        return (DropdownFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
