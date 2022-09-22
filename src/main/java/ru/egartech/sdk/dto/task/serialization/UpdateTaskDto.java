package ru.egartech.sdk.dto.task.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.serialization.assigner.Assigner;
import ru.egartech.sdk.dto.task.serialization.customfield.update.BindFieldDto;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskDto extends RequestTaskDto {

    @NonNull
    private final String id;
    private String name;

    @JsonProperty("custom_id")
    private String customId;

    @JsonProperty("assignees")
    private Assigner assignees;

    @JsonProperty("custom_fields")
    @Singular
    private List<BindFieldDto> customFields;

}
