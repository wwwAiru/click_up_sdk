package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.taskmapper.dto.task.assigner.AssignerDto;
import ru.egartech.taskmapper.dto.task.customfield.deserializer.CustomFieldStdConverter;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;
import ru.egartech.taskmapper.exception.customfield.CustomFieldNotFoundException;

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

    @JsonProperty("custom_fields")
    @JsonDeserialize(converter = CustomFieldStdConverter.class)
    private Map<String, CustomField<?>> customFields = new HashMap<>();

    public <T extends CustomField<?>> T customField(String id) {
        CustomField<?> customField = customFields.get(id);

        if (customField == null) throw new CustomFieldNotFoundException(id);

        return (T) customField;
    }

}
