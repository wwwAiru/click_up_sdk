package ru.egartech.sdk.dto.task.deserialization.customfield.field.text;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.CustomField;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TextFieldDto extends CustomField<String> {
}
