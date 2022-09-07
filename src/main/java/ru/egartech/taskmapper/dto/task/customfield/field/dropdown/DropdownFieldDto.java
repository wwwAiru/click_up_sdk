package ru.egartech.taskmapper.dto.task.customfield.field.dropdown;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(converter = DropdownFieldConverter.class)
public class DropdownFieldDto extends CustomField<DropdownOption> {

    @JsonProperty(value = "type_config", access = JsonProperty.Access.WRITE_ONLY)
    private DropdownTypeConfig dropdownTypeConfig;

}
