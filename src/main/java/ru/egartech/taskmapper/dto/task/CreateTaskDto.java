package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CreateTaskDto extends RequestTaskDto {

    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @JsonProperty("custom_fields")
    private List<CreateFieldDto> customFields;
}
