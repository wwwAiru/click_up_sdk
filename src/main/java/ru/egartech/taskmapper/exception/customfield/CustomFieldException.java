package ru.egartech.taskmapper.exception.customfield;

public class CustomFieldException extends RuntimeException {

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
