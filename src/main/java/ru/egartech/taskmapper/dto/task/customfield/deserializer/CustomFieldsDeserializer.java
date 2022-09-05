package ru.egartech.taskmapper.dto.task.customfield.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.converter.FieldConverter;
import ru.egartech.taskmapper.dto.task.customfield.factory.CustomFieldFactoryProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class CustomFieldsDeserializer extends JsonDeserializer<CustomField<?>> {

    private final Map<FieldType, FieldConverter<?>> map;

    public CustomFieldsDeserializer(List<FieldConverter<?>> fieldConverters) {
        this.map = fieldConverters.stream().collect(Collectors.toMap(FieldConverter::getType, Function.identity()));
    }

    @Override
    public CustomField<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.readValueAsTree();

        if (treeNode.get("name") == null || treeNode.get("value") == null) {
            return CustomFieldFactoryProvider.createCustomField(
                    FieldType.EMPTY, Map.of("id", "", "name", "", "value", "")
            );
        }

        String typeString = treeNode.get("type").toString().replaceAll("\"", "");
        String name = treeNode.get("name").toString().replaceAll("\"", "");

        FieldType type = FieldType.of(typeString);

        FieldConverter<?> fieldConverter = map.get(type);

        if (Arrays.asList(FieldType.values()).contains(type)) {
            return (CustomField<?>) fieldConverter.convert(treeNode);
        }

        return CustomFieldFactoryProvider.createCustomField(
                FieldType.UNKNOWN,
                Map.of("id", "", "name", name, "value", treeNode.get("value")
                        .toString()
                        .replaceAll("\"", ""))
        );
    }
}
