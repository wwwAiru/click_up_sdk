package ru.egartech.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.AuthorizationRequestInterceptor;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.api.TaskClientImpl;

import java.util.List;

@Configuration
@ConditionalOnClass(TaskClient.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class TaskClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TaskClient taskClient() {
        return new TaskClientImpl(restTemplate(), objectMapper());
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(authorizationRequestInterceptor()));
        return restTemplate;
    }

    @Bean
    public AuthorizationRequestInterceptor authorizationRequestInterceptor() {
        return new AuthorizationRequestInterceptor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
