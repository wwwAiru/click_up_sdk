package ru.egartech.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.sdk.dto.task.TaskDto;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {FileProvider.class})
@AutoConfigureJson
public class TaskDtoMappingTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private FileProvider fileProvider;

    @Nested
    @DisplayName("Deserialize")
    class Deserialize {

        @SneakyThrows
        @Test
        @DisplayName("Verify that task is deserializing correctly")
        public void taskDtoDeserializeTest() {
            // given
            TaskDto taskById;

            // when
            taskById = mapper.readValue(fileProvider.getTaskByIdFile(), TaskDto.class);

            // then
            assertThat(taskById)
                    .isNotNull()
                    .isNotEqualTo(new TaskDto())
                    .extracting(TaskDto::getId)
                    .isEqualTo("2rgq20y");

        }

        @SneakyThrows
        @Test
        @DisplayName("Verify that custom fields in task is deserializing correctly")
        public void customFieldsDeserializeTest() {
            // given
            TaskDto taskById;

            // when
            taskById = mapper.readValue(fileProvider.getTaskByIdFile(), TaskDto.class);

            // then
            assertThat(taskById)
                    .extracting(TaskDto::getCustomFields)
                    .isNotNull()
                    .extracting(Map::size)
                    .isEqualTo(30);
        }
    }

}
