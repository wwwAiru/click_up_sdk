package ru.egartech.taskmapper.dto.task.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {

    public static final Status OPEN = new Status("Open", "#d3d3d3", 0, "open");

    private String status;
    private String color;

    @JsonProperty("orderindex")
    private int orderIndex;

    private String type;

}
