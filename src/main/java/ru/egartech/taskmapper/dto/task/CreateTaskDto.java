package ru.egartech.taskmapper.dto.task;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class CreateTaskDto extends RequestTaskDto {

    @NonNull
    private final String id;

    @NonNull
    private final String name;

}
