package ru.egartech.taskmapper.dto.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class CreateFieldDto {

    private final String id;
    private final Object value;

}
