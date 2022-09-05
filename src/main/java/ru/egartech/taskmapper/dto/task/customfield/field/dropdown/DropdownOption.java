package ru.egartech.taskmapper.dto.task.customfield.field.dropdown;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DropdownOption {

    private String id;

    private String name;

    @JsonProperty("orderindex")
    private int orderIndex;

}
