package ru.egartech.taskmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.egartech.taskmapper.FileProvider.PREFIX;

@SpringBootTest(classes = {FileProvider.class})
@AutoConfigureJson
public class TaskDtoDeserializeTest {

    @Autowired
    private FileProvider fileProvider;

    @SneakyThrows
    @Test
    @DisplayName("Verify that task can be deserialized")
    public void deserializeTest() {
        File jsonFile = ResourceUtils.getFile(PREFIX.concat(fileProvider.getTaskByIdJsonName()));
        TaskDto taskDto = mapper.readValue(jsonFile, TaskDto.class);
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

        Map<String, Object> taskFromJsonFile = mapper.readValue(jsonFile, new TypeReference<>() {
        });
        List<Map<String, Object>> customFieldsFromJsonInFile =
                (List<Map<String, Object>>) taskFromJsonFile.get("custom_fields");

        assertEquals(customFieldsFromJsonInFile.size(), taskDto.getCustomFields().size());
    }

    @Autowired
    private ObjectMapper mapper;

}
