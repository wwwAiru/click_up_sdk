package ru.egartech.taskmapper.exception.customfield;

public class CustomFieldValueNotFoundException extends CustomFieldException{
    public CustomFieldValueNotFoundException() {
        super();
    }

    public CustomFieldValueNotFoundException(String message) {
        super(message);
    }

    public CustomFieldValueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomFieldValueNotFoundException(Throwable cause) {
        super(cause);
    }
}
