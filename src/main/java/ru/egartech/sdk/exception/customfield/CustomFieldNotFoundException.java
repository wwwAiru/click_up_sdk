package ru.egartech.sdk.exception.customfield;

public class CustomFieldNotFoundException extends CustomFieldException {
    public CustomFieldNotFoundException() {
        super();
    }

    public CustomFieldNotFoundException(String message) {
        super(message);
    }

    public CustomFieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomFieldNotFoundException(Throwable cause) {
        super(cause);
    }
}
