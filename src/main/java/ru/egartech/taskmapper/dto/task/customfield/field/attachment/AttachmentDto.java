package ru.egartech.taskmapper.dto.task.customfield.field.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDto {

    private String id;
    private String url;

}
