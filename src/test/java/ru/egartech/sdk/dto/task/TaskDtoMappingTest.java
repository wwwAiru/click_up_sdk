package ru.egartech.sdk.dto.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.egartech.sdk.AbstractSpringBootContext;
import ru.egartech.sdk.config.TaskDtoMappingTestConfig;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.property.FileProvider;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes = TaskDtoMappingTestConfig.class)
public class TaskDtoMappingTest extends AbstractSpringBootContext {
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
