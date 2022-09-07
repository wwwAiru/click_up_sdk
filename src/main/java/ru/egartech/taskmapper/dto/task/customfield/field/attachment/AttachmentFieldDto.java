package ru.egartech.taskmapper.dto.task.customfield.field.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AttachmentFieldDto extends CustomField<List<AttachmentDto>> {
    @JsonIgnore
    public String getUrl() {
        return getValue()
                .stream()
                .findFirst()
                .orElseThrow()
                .getUrl();
    }
}
