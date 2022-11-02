package ru.egartech.sdk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.egartech.sdk.util.MessageSourceUtils;

@Configuration
@RequiredArgsConstructor
public class MessageSourceUtilsAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public MessageSourceUtils messageSourceUtils(MessageSource messageSource) {
        return new MessageSourceUtils(messageSource);
    }
}
