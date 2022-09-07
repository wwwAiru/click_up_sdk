package ru.egartech.taskmapper.dto.task.customfield.deserializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomFieldStdConverter extends StdConverter<List<AbstractField<?>>, Map<String, AbstractField<?>>> {
    @Override
    public Map<String, AbstractField<?>> convert(List<AbstractField<?>> value) {
        return value.stream()
                .filter(p -> !Objects.equals(p.getId(), ""))
                .collect(Collectors.toMap(AbstractField::getId, v -> v));
    }
}
