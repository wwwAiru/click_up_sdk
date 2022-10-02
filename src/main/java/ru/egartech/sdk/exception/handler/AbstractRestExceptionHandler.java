package ru.egartech.sdk.exception.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.egartech.sdk.exception.dto.ApiErrorDto;
import ru.egartech.sdk.util.MessageSourceUtils;

import java.util.Date;

@Order
@RestControllerAdvice
@RequiredArgsConstructor
public abstract class AbstractRestExceptionHandler {
    protected final MessageSourceUtils messageSourceUtils;

    protected ApiErrorDto buildMessage(MessageSourceUtils messageSourceUtils,
                                       Exception e,
                                       WebRequest webRequest,
                                       String srcToMessageSource,
                                       Object... args) {
        String message = messageSourceUtils.buildMessage(srcToMessageSource, webRequest, args);
        String milliseconds = String.valueOf(new Date().getTime());
        return ApiErrorDto.builder()
                .milliseconds(milliseconds)
                .propertyClass(e.getClass().getSimpleName())
                .message(message)
                .build();
    }


    protected ApiErrorDto buildMessage(RuntimeException e) {
        return ApiErrorDto.builder()
                .milliseconds(String.valueOf(new Date().getTime()))
                .propertyClass(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .build();
    }
}
