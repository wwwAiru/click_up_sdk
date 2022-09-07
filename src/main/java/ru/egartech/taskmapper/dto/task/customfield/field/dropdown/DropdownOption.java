package ru.egartech.taskmapper.dto.task.customfield.field.dropdown;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DropdownOption {

    private String id;

    private String name;

    @JsonProperty("orderindex")
    private int orderIndex;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer value;

    public DropdownOption(int value) {
        this.value = value;
    }

}
