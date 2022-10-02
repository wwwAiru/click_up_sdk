package ru.egartech.sdk.dto.task.deserialization.customfield;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.sdk.dto.task.deserialization.customfield.deserializer.CustomFieldStdConverter;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;
import ru.egartech.sdk.exception.customfield.CustomFieldNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldsDto {
    @JsonProperty("fields")
    @JsonDeserialize(converter = CustomFieldStdConverter.class)
    private Map<String, CustomField<?>> customFields = new HashMap<>();

    public <T extends CustomField<?>> T customField(String id) {
        CustomField<?> customField = customFields.get(id);
        if (customField == null) throw new CustomFieldNotFoundException(id);
        return (T) customField;
    }
}
