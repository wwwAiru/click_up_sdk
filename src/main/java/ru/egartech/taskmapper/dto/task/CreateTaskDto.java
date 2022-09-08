package ru.egartech.taskmapper.dto.task;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CreateTaskDto extends RequestTaskDto {

    @NonNull
    private final String id;

    @NonNull
    private final String name;

}
