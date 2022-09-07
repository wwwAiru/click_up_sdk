package ru.egartech.taskmapper.dto.task.customfield.field.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDto {

    private String id;
    private String url;

}
