package ru.egartech.taskmapper.dto.task.customfield.field.label;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelOptionDto {

    private String id;
    private String label;

    public LabelOptionDto(String id) {
        this.id = id;
    }

}
