package ru.egartech.taskmapper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FileProvider {

    public static final String PREFIX = "classpath:";

    @Value("${clickup.json.response.file-name.task-by-id}")
    private String taskByIdJsonName;

    @Value("${clickup.json.response.file-name.task-by-custom-fields}")
    private String taskByCustomFieldsJsonName;

}
