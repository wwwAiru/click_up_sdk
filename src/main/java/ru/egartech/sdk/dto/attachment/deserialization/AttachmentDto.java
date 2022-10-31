package ru.egartech.sdk.dto.attachment.deserialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDto {
    private String id;
    private String version;
    private String date;
    private String title;
    private String extension;
    private String url;
}
