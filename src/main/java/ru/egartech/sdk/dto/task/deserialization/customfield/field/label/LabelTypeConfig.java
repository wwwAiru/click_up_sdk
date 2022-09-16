package ru.egartech.sdk.dto.task.deserialization.customfield.field.label;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.sdk.exception.customfield.CustomFieldValueNotFoundException;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelTypeConfig {

    @JsonProperty("options")
    private List<LabelOptionDto> labelOptionDtos = new ArrayList<>();

    public LabelOptionDto byLabelId(String id) {
        return labelOptionDtos.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(CustomFieldValueNotFoundException::new);
    }

}
