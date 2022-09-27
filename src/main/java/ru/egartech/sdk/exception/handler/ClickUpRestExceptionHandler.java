package ru.egartech.sdk.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import ru.egartech.sdk.exception.dto.ApiErrorDto;
import ru.egartech.sdk.util.MessageSourceUtils;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ClickUpRestExceptionHandler extends AbstractRestExceptionHandler {
    public ClickUpRestExceptionHandler(MessageSourceUtils messageSourceUtils) {
        super(messageSourceUtils);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    private ApiErrorDto handleHttpClientErrorException(HttpClientErrorException e) {
        return buildMessage(e);
    }
}
