package ru.egartech.taskmapper.dto.task.customfield.field.relationship;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelationShipValueDto {

    private String id;
    private String name;
    private String status;
    private String subcategoryId;

}
