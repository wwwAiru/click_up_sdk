package ru.egartech.sdk.dto.task.deserialization.customfield.deserializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomFieldStdConverter extends StdConverter<List<CustomField<?>>, Map<String, CustomField<?>>> {
    @Override
    public Map<String, CustomField<?>> convert(List<CustomField<?>> value) {
        return value.stream()
                .filter(p -> !Objects.equals(p.getId(), ""))
                .collect(Collectors.toMap(CustomField::getId, Function.identity()));
    }
}
