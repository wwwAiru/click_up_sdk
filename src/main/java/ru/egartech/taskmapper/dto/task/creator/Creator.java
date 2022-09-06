package ru.egartech.taskmapper.dto.task.creator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Creator {

    private int id;
    private String username;
    private String color;

    @JsonProperty("profile_picture")
    private String profilePicture;

}
