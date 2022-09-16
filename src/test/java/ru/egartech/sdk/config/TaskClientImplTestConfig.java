package ru.egartech.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.api.impl.TaskClientImpl;

@ContextConfiguration(classes = RestTemplate.class)
public class TaskClientImplTestConfig extends AbstractBaseConfig {
    @Bean
    public TaskClient taskClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new TaskClientImpl(restTemplate, objectMapper);
    }
}
