package ru.egartech.sdk.dto.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "ofName")
@EqualsAndHashCode(callSuper = true)
public class CreateTaskDto extends RequestTaskDto {

    private final String name;
    private String id = "ignored";

}
