package ru.egartech.sdk.dto.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.sdk.AbstractSpringBootContext;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.property.FileProvider;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тестирование TaskDtoMapper")
public class TaskDtoMappingTest extends AbstractSpringBootContext {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private FileProvider fileProvider;


    @SneakyThrows
    @Test
    @DisplayName("Тестирование того, что задача десериализуется правильно")
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
        assertThat(taskById)
                .extracting(TaskDto::getDateCreated)
                .isEqualTo("1657775303282");
        assertThat(taskById)
                .extracting(TaskDto::getDateUpdated)
                .isEqualTo("1662458078921");
        assertThat(taskById)
                .extracting(TaskDto::getDateClosed)
                .isEqualTo(null);
        assertThat(taskById)
                .extracting(TaskDto::getDueDate)
                .isEqualTo("1663808400000");

    }

    @SneakyThrows
    @Test
    @DisplayName("Тестирование того, что кастомные поля десериализуются правильно")
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
