package ru.egartech.sdk.dto.customfield.serialization;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class UpdateFieldDto {
    private final Object value;
}
