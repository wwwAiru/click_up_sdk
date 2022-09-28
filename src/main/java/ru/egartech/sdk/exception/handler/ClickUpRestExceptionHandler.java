package ru.egartech.sdk.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.egartech.sdk.exception.clickup.ClickUpException;
import ru.egartech.sdk.exception.dto.ApiErrorDto;
import ru.egartech.sdk.util.MessageSourceUtils;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ClickUpRestExceptionHandler extends AbstractRestExceptionHandler {
    public ClickUpRestExceptionHandler(MessageSourceUtils messageSourceUtils) {
        super(messageSourceUtils);
    }

    @ExceptionHandler(ClickUpException.class)
    private ApiErrorDto handleClickUpException(ClickUpException e) {
        return buildMessage(e);
    }
}
