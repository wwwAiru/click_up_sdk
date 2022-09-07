package ru.egartech.taskmapper.dto.task.customfield.field.label;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelTypeConfig {

    @JsonProperty("options")
    private List<LabelOptionDto> labelOptionDtos;

    public LabelOptionDto byLabelId(String id) {
        return labelOptionDtos.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
