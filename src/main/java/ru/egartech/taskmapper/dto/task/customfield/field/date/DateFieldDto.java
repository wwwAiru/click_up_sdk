package ru.egartech.taskmapper.dto.task.customfield.field.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.AbstractField;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DateFieldDto extends AbstractField<String> {
}
