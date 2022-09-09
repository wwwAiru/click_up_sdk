package ru.egartech.taskmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import ru.egartech.taskmapper.api.TaskClient;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.TasksDto;
import ru.egartech.taskmapper.dto.task.customfield.field.CustomField;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.egartech.taskmapper.FileProvider.PREFIX;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TaskClientImplUnitTest {

    @Autowired
    private FileProvider fileProvider;

    @Mock
    private TaskClient taskClient;

    @SneakyThrows
    @Test
    @DisplayName("Verify that taskClient.getTaskById() has been called")
    public void getTaskByIdTest() {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = ResourceUtils.getFile(PREFIX.concat(fileProvider.getTaskByIdJsonName()));

        Map<String, Object> taskByIdJson = mapper.readValue(jsonFile, new TypeReference<>() {});
        TaskDto taskDto = mapper.readValue(jsonFile, TaskDto.class);

        when(taskClient.getTaskById(anyString(), anyBoolean())).thenReturn(taskDto);
        taskClient.getTaskById("taskId", false);
        verify(taskClient, times(1)).getTaskById(anyString(), anyBoolean());

        List<Map<String, CustomField<?>>> customFieldsFromJson =
                (List<Map<String, CustomField<?>>>) taskByIdJson.get("custom_fields");
        Map<String, CustomField<?>> customFields = taskDto.getCustomFields();

        assertThat(taskDto.getId()).isEqualTo(taskByIdJson.get("id"));
        assertThat(customFields.size()).isEqualTo(customFieldsFromJson.size());
    }

    @SneakyThrows
    @Test
    @DisplayName("Verify that taskClient.getTasksByCustomField() has been called")
    public void getTasksByCustomFields() {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = ResourceUtils.getFile(PREFIX.concat(fileProvider.getTaskByCustomFieldsJsonName()));
        Map<String, Object> taskByCustomFieldsJson = mapper.readValue(jsonFile, new TypeReference<>() {});
        TasksDto tasksDto = mapper.readValue(jsonFile, TasksDto.class);

        lenient().when(taskClient.getTasksByCustomField(anyString(), any())).thenReturn(tasksDto);
        taskClient.getTasksByCustomField("listId");
        verify(taskClient, times(1)).getTasksByCustomField(anyString(), any());

        assertThat(tasksDto.getTasks())
                .hasSize(((List<Map<String, Object>>) taskByCustomFieldsJson.get("tasks")).size());
    }

}
