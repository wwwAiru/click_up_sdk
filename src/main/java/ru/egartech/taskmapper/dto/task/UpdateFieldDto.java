package ru.egartech.taskmapper.dto.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class UpdateFieldDto {

    private final Object value;

}