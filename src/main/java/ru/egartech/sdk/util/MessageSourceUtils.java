package ru.egartech.sdk.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
@RequiredArgsConstructor
public class MessageSourceUtils {
    private final MessageSource messageSource;

    public String buildMessage(String code, WebRequest request, Object... args) {
        return messageSource.getMessage(code, args, request.getLocale());
    }
}
