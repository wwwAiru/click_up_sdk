package ru.egartech.taskmapper.dto.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class BindFieldDto {

    private final String fieldId;
    private final Object value;

}
