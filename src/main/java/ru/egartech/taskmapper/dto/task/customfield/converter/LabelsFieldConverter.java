package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelOptionDto;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelTypeConfig;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelsFieldDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LabelsFieldConverter implements FieldConverter<LabelsFieldDto> {
    private static final FieldType TYPE = FieldType.LABELS;

    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public LabelsFieldDto convert(TreeNode treeNode) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", treeNode.get("name").toString().replaceAll("\"", ""));
        values.put("id", treeNode.get("id").toString().replaceAll("\"", ""));

        LabelTypeConfig typeConfig = mapper.readValue(treeNode.get("type_config").traverse(), LabelTypeConfig.class);
        Collection<String> value = mapper.readValue(treeNode.get("value").traverse(), new TypeReference<>() {
        });
        List<LabelOptionDto> labelOptionDtos = value.stream()
                .map(typeConfig::byLabelId)
                .collect(Collectors.toList());

        values.put("value", labelOptionDtos);

        return (LabelsFieldDto) CustomFieldFactoryProvider.createCustomField(TYPE, values);
    }

    @Override
    public FieldType getType() {
        return TYPE;
    }
}
