package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@JsonInclude(Include.NON_NULL)
public class UpdateTaskDto extends RequestTaskDto {

    private final String id;

    @JsonProperty("custom_id")
    private String customId;

}
