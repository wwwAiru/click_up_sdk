package ru.egartech.sdk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.api.impl.CustomFieldClientImpl;

@Configuration
@RequiredArgsConstructor
public class CustomFieldClientAutoConfiguration {
    private final RestTemplate restTemplate;

    @Bean
    @ConditionalOnMissingBean
    public CustomFieldClient customFieldClient() {
        return new CustomFieldClientImpl(restTemplate);
    }
}
