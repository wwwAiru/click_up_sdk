package ru.egartech.sdk.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.AbstractSpringBootContext;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.serialization.UpdateTaskDto;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;

import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@DisplayName("Моковое тестирование TaskClientImpl")
public class TaskClientImplTest extends AbstractSpringBootContext {
    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    @Autowired
    private TaskClient taskClient;

    @SneakyThrows
    @Test
    @DisplayName("Тестирование, что restTemplate в методе getTaskById был вызван")
    public void taskByIdRestTemplateTest() {
        // given
        TaskDto taskDto = new TaskDto();
        // when
        when(restTemplate.getForObject(any(), any(), anyMap())).thenReturn(taskDto);
        // then
        taskClient.getTaskById("id", false);
        verify(restTemplate, times(1)).getForObject(anyString(), any(), anyMap());
    }

    @SneakyThrows
    @Test
    @DisplayName("Тестирование, что restTemplate в методе getTasksByCustomField был вызван")
    public void tasksByCustomFieldsRestTemplateTest() {
        // given
        TasksDto tasksDto = new TasksDto();
        // when
        when(restTemplate.getForObject(any(), any(), anyMap())).thenReturn(tasksDto);
        // then
        taskClient.getTasksByCustomFields(123, false);
        verify(restTemplate, times(1)).getForObject(anyString(), any(), anyMap());
    }

    @Test
    @DisplayName("Тестирование, что restTemplate в методе updateTask был вызван 2+N раза")
    public void updateTaskRestTemplateTest() {
        // given
        int randomBindFieldDtoCount = new Random().nextInt(10);
        // when
        UpdateTaskDto updateTaskDto = UpdateTaskDto.builder()
                .id("any")
                .customFields(Stream
                        .generate(() -> BindFieldDto.of("", ""))
                        .limit(randomBindFieldDtoCount)
                        .toList())
                .build();

        taskClient.updateTask(updateTaskDto);
        // then
        verify(restTemplate, times(1)).put(any(), any(), anyMap());
        verify(restTemplate, times(1)).getForObject(any(), any(), anyMap());
        verify(restTemplate, times(randomBindFieldDtoCount)).postForObject(any(), any(), any(), anyMap());
    }
}
