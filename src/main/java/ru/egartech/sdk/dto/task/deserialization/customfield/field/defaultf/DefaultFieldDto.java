package ru.egartech.sdk.dto.task.deserialization.customfield.field.defaultf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DefaultFieldDto extends CustomField<Object> {
}
