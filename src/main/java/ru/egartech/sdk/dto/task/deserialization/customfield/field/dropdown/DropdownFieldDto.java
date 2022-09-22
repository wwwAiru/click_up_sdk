package ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(converter = DropdownFieldConverter.class)
public class DropdownFieldDto extends CustomField<DropdownOption> {

    @JsonProperty(value = "type_config")
    private DropdownTypeConfig dropdownTypeConfig;

}
