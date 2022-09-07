package ru.egartech.taskmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ResourceUtils;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JsonTest
@AutoConfigureJson
@ComponentScan("ru.egartech.taskmapper")
public class TaskDtoDeserializeTest {

    @Value("${clickup.json.response.file-name}")
    private String clickUpResponseFileName;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    public void deserializeTest() {
        File jsonInFile = ResourceUtils.getFile("classpath:" + clickUpResponseFileName);

        TaskDto taskDto = mapper.readValue(jsonInFile, TaskDto.class);
        assertNotNull(taskDto);

        // выгрузка кастомных филдов, а также проверка, что TaskDto не равно null
        AbstractObjectAssert<?, Map<String, CustomField<?>>> extractingCustomFields = assertThat(taskDto)
                .isNotNull()
                .isNotEqualTo(new TaskDto())
                .extracting(TaskDto::getCustomFields)
                // проверка, что кастомные филды не равны null
                .isNotNull();

        // проверка, что id кастомных филдов не равны null
        extractingCustomFields
                .extracting(Map::keySet)
                .isNotNull();

        // проверка, что значения кастомных филдов не равны null
        extractingCustomFields
                .extracting(Map::values)
                .isNotNull();
    }

}
