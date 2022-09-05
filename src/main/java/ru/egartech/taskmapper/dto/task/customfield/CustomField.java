package ru.egartech.taskmapper.dto.task.customfield;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.egartech.taskmapper.dto.task.customfield.deserializer.CustomFieldsDeserializer;

@JsonDeserialize(using = CustomFieldsDeserializer.class)
public interface CustomField<T> {

    String getId();

    String getName();

    FieldType getType();

    T getValue();

    static String toId(CustomField<?> customField) {
        return customField.getId();
    }

    static CustomField<?> toField(CustomField<?> customField) {
        return customField;
    }

}
