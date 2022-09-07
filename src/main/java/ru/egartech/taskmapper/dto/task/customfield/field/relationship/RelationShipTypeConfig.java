package ru.egartech.taskmapper.dto.task.customfield.field.relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelationShipTypeConfig {

    @JsonProperty(value = "subcategory_id", access = JsonProperty.Access.WRITE_ONLY)
    private String subcategoryId;

}
