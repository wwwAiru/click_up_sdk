package ru.egartech.taskmapper.dto.task.customfield;

import java.util.Locale;

public enum FieldType {
    EMPTY,
    UNKNOWN,
    LIST_RELATIONSHIP,
    DROP_DOWN,
    LABELS,
    SHORT_TEXT,
    DATE,
    PHONE,
    EMAIL,
    URL,
    ATTACHMENT,
    NUMBER;

    public static FieldType of(String name) {
        return FieldType.valueOf(name.toUpperCase(Locale.ROOT));
    }
}
