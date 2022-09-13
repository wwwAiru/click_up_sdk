package ru.egartech.sdk.exception.token;

import ru.egartech.sdk.exception.ApplicationException;

public class TokensNotSpecifiedException extends ApplicationException {
    public TokensNotSpecifiedException() {
        super();
    }

    public TokensNotSpecifiedException(String message) {
        super(message);
    }

    public TokensNotSpecifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokensNotSpecifiedException(Throwable cause) {
        super(cause);
    }
}
