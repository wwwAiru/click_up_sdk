package ru.egartech.sdk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.AuthorizationRequestInterceptor;
import ru.egartech.sdk.property.AuthorizationProperties;

@Configuration
@ConditionalOnProperty(prefix = "clickup.api", name = "tokens", matchIfMissing = true)
@EnableConfigurationProperties(value = AuthorizationProperties.class)
public class RestTemplateAutoConfiguration {

    @Bean
    public RestTemplate restTemplate(AuthorizationProperties properties) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(authorizationRequestInterceptor(properties));
        return restTemplate;
    }

    @Bean
    public AuthorizationRequestInterceptor authorizationRequestInterceptor(AuthorizationProperties properties) {
        return new AuthorizationRequestInterceptor(properties);
    }

}
