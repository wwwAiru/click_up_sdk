package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;

public interface AbstractCustomFieldFactory<T> {

    CustomField<T> createField(String id, String name, T value);

    FieldType getType();

}
