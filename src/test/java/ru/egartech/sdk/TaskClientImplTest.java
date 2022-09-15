package ru.egartech.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.api.TaskClientImpl;
import ru.egartech.sdk.dto.task.BindFieldDto;
import ru.egartech.sdk.dto.task.TaskDto;
import ru.egartech.sdk.dto.task.TasksDto;
import ru.egartech.sdk.dto.task.UpdateTaskDto;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TaskClientImpl.class})
public class TaskClientImplTest {

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
