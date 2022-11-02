package ru.egartech.sdk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.egartech.sdk.exception.handler.ClickUpRestExceptionHandler;
import ru.egartech.sdk.util.MessageSourceUtils;

@Configuration
@RequiredArgsConstructor
public class ClickUpRestExceptionHandlerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ClickUpRestExceptionHandler clickUpRestExceptionHandler(MessageSourceUtils messageSourceUtils) {
        return new ClickUpRestExceptionHandler(messageSourceUtils);
    }
}
