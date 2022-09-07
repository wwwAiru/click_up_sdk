package ru.egartech.taskmapper.exception.customfield;

import ru.egartech.taskmapper.exception.ApplicationException;

public class CustomFieldException extends ApplicationException {

    public CustomFieldException() {
        super();
    }

    public CustomFieldException(String message) {
        super(message);
    }

    public CustomFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomFieldException(Throwable cause) {
        super(cause);
    }
}
