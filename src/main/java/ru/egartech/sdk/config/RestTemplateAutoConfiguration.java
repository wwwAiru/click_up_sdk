package ru.egartech.sdk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.property.AuthorizationProperties;
import ru.egartech.sdk.api.AuthorizationRequestInterceptor;

@Configuration
@ConditionalOnProperty(prefix = "clickup.api", name = "tokens", matchIfMissing = true)
@EnableConfigurationProperties(value = AuthorizationProperties.class)
@RequiredArgsConstructor
public class RestTemplateAutoConfiguration {

    private final AuthorizationProperties properties;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(authorizationRequestInterceptor());
        return restTemplate;
    }

    @Bean
    public AuthorizationRequestInterceptor authorizationRequestInterceptor() {
        return new AuthorizationRequestInterceptor(properties);
    }

}
