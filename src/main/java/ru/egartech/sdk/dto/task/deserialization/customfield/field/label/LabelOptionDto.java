package ru.egartech.sdk.dto.task.deserialization.customfield.field.label;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    /**
     * Конструктор нужен для создания из строки в объект LabelOptionDto, когда присутствует только поле id.
     * Используется как дженерик тип в {@link LabelsFieldDto}.
     * Используется в списке из опций {@link LabelTypeConfig}.
     *
     * @param id идентификатор в виде строки
     * @see LabelTypeConfig
     * @see LabelsFieldDto
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public LabelOptionDto(String id) {
        this.id = id;
    }

}
