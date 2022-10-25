package ru.egartech.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.api.TaskClient;
import ru.egartech.sdk.api.impl.TaskClientImpl;

@Configuration
@ConditionalOnClass(TaskClient.class)
@RequiredArgsConstructor
public class TaskClientAutoConfiguration {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CustomFieldClient customFieldClient;

    @Bean
    @ConditionalOnMissingBean
    public TaskClient taskClient() {
        return new TaskClientImpl(customFieldClient, restTemplate, objectMapper);
    }
}
