package ru.egartech.sdk.dto.task.customfield.field.dropdown;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.sdk.exception.customfield.CustomFieldValueNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DropdownTypeConfig {

    @JsonProperty("options")
    private List<DropdownOption> labelOptions = new ArrayList<>();

    public DropdownOption byOrderIndex(Integer orderIndex) {
        return labelOptions.stream()
                .filter(o -> Objects.equals(o.getOrderIndex(), orderIndex))
                .findFirst()
                .orElseThrow(CustomFieldValueNotFoundException::new);
    }

}
