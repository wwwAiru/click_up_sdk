package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentDto;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentFieldDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AttachmentFieldConverter implements FieldConverter<AttachmentFieldDto> {
    private static final FieldType TYPE = FieldType.ATTACHMENT;

    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public AttachmentFieldDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));
        values.put("value", mapper.readValue(treeNode.get("value").traverse(), new TypeReference<List<AttachmentDto>>() {
        }));

        return (AttachmentFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
