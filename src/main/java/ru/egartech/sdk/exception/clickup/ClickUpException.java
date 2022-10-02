package ru.egartech.sdk.exception.clickup;

import ru.egartech.sdk.exception.ApplicationException;

public class ClickUpException extends ApplicationException {
    public ClickUpException() {
        super();
    }

    public ClickUpException(String message) {
        super(message);
    }

    public ClickUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClickUpException(Throwable cause) {
        super(cause);
    }
}
