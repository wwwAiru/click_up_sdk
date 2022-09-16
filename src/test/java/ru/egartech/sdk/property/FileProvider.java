package ru.egartech.sdk.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration
public class FileProvider {

    @Value("${clickup.response.json.file-name.task-by-id}")
    private String taskByIdJsonName;

    @Value("${clickup.response.json.file-name.task-by-custom-fields}")
    private String taskByCustomFieldsJsonName;

    public File getTaskByIdFile() throws FileNotFoundException {
        return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX
                .concat(taskByIdJsonName));
    }

    public File getTaskByCustomFields() throws FileNotFoundException {
        return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX
                .concat(taskByCustomFieldsJsonName));
    }

}