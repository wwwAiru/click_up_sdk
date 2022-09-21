package ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    private int value;

    /**
     * Конструктор нужен для создания из строки в объект LabelOptionDto, когда присутствует только поле value.
     * Используется как дженерик тип в {@link DropdownFieldDto}.
     * Используется в списке из опций {@link DropdownTypeConfig}.
     *
     * @param value номер индекса в списке опций в typeConfig
     * @see DropdownFieldDto
     * @see DropdownTypeConfig
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public DropdownOption(@JsonProperty("value") int value) {
        this.value = value;
    }

}
