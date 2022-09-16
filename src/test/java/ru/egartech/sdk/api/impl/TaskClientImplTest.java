package ru.egartech.sdk.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.AbstractSpringBootContext;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.config.TaskClientImplTestConfig;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.serialization.UpdateTaskDto;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = TaskClientImplTestConfig.class)
public class TaskClientImplTest extends AbstractSpringBootContext {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    @Autowired
    private TaskClient taskClient;

    @SneakyThrows
    @Test
    @DisplayName("Verify that restTemplate in getTaskById() has been called")
    public void taskByIdRestTemplateCallTest() {
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
    @DisplayName("Verify that restTemplate in getTasksByCustomField() has been called")
    public void tasksByCustomFieldsRestTemplateTest() {
        // given
        TasksDto tasksDto = new TasksDto();

        // when
        when(restTemplate.getForObject(any(), any(), anyMap())).thenReturn(tasksDto);

        // then
        taskClient.getTasksByCustomFields(123);
        verify(restTemplate, times(1)).getForObject(anyString(), any(), anyMap());
    }

    @Test
    @DisplayName("Verify that restTemplate in updateTask() has been called 2+N times")
    public void updateTaskRestTemplateTest() {
        // given
        int randomBindFieldDtoCount = new Random().nextInt(10);

        // when
        taskClient.updateTask(UpdateTaskDto
                .ofTaskId("any")
                .bindCustomFields(
                        Stream
                                .generate(() -> BindFieldDto.of("", ""))
                                .limit(randomBindFieldDtoCount)
                                .collect(Collectors.toList())
                )
        );

        // then
        verify(restTemplate, times(1)).put(any(), any(), anyMap());
        verify(restTemplate, times(1)).getForObject(any(), any(), anyMap());
        verify(restTemplate, times(randomBindFieldDtoCount)).postForObject(any(), any(), any(), anyMap());
    }

}
