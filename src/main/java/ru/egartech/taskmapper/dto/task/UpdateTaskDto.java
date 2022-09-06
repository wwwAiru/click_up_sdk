package ru.egartech.taskmapper.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
public class UpdateTaskDto extends RequestTaskDto {

    @JsonProperty("custom_id")
    private String customId;

}
