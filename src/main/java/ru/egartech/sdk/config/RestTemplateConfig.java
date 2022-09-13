package ru.egartech.sdk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.AuthorizationRequestInterceptor;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final AuthorizationRequestInterceptor interceptor;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(interceptor));
        return restTemplate;
    }

}
