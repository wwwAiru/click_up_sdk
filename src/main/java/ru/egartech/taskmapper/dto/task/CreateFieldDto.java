package ru.egartech.taskmapper.dto.task;

import lombok.Data;

@Data
public class CreateFieldDto {

    private final String id;
    private final Object value;

}
