package ru.egartech.taskmapper.dto.task.customfield.field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;

@Data
@SuperBuilder
@AllArgsConstructor
@Accessors(chain = true)
public abstract class AbstractField<T> implements CustomField<T> {

    @JsonIgnore
    protected String id;

    protected String name;

    protected final FieldType type;

    protected T value;

}
