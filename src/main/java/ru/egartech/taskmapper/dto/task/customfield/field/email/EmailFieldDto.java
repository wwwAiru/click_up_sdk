package ru.egartech.taskmapper.dto.task.customfield.field.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.text.TextFieldDto;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmailFieldDto extends TextFieldDto {
}
