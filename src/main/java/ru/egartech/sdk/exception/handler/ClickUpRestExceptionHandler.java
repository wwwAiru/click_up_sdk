package ru.egartech.sdk.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.egartech.sdk.exception.clickup.ClickUpException;
import ru.egartech.sdk.exception.dto.ApiErrorDto;
import ru.egartech.sdk.exception.dto.ViolationDto;
import ru.egartech.sdk.util.MessageSourceUtils;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ViolationDto> onConstraintValidationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(violation -> ViolationDto.builder()
                        .fieldName(violation.getPropertyPath().toString())
                        .message(violation.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ViolationDto> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ViolationDto(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
