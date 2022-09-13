package ru.egartech.sdk.dto.task.customfield.field.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;
import ru.egartech.sdk.dto.task.customfield.field.CustomField;
import ru.egartech.sdk.exception.ApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AttachmentFieldDto extends CustomField<List<AttachmentDto>> {
    protected List<AttachmentDto> value = new ArrayList<>();

    @JsonIgnore
    public String getUrl() {
        if (getValue().size() > 1) {
            throw new ApplicationException("Элементов в коллекций больше чем 1");
        }
        return Objects.requireNonNull(CollectionUtils.firstElement(getValue())).getUrl();
    }
}
