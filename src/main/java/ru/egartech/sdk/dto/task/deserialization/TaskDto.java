package ru.egartech.sdk.dto.task.deserialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.sdk.dto.task.deserialization.customfield.assigner.AssignerDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.deserializer.CustomFieldStdConverter;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;
import ru.egartech.sdk.dto.task.deserialization.list.ListDto;
import ru.egartech.sdk.exception.customfield.CustomFieldNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subtasks")
    private List<TaskDto> subtasks = new ArrayList<>();

    @JsonProperty("assignees")
    private List<AssignerDto> assigners = new ArrayList<>();

    @JsonProperty("list")
    private ListDto list;

    @JsonProperty("custom_fields")
    @JsonDeserialize(converter = CustomFieldStdConverter.class)
    private Map<String, CustomField<?>> customFields = new HashMap<>();

    public <T extends CustomField<?>> T customField(String id) {
        CustomField<?> customField = customFields.get(id);

        if (customField == null) throw new CustomFieldNotFoundException(id);

        return (T) customField;
    }

    public <T extends CustomField<?>> T customField(String id, Class<T> tClass) {
        CustomField<?> customField = customFields.get(id);

        if (customField == null) throw new CustomFieldNotFoundException(id);

        return tClass.cast(customField);
    }

    /**
     * Возвращает список кастомных полей для последующего каста. Неактуален в связи с наличием метода {@link #customField(String field_id)}.
     *
     * @see #customField(String field_id)
     * */
    @Deprecated
    public Map<String, CustomField<?>> getCustomFields() {
        return customFields;
    }
}
