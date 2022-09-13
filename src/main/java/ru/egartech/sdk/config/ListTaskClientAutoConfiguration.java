package ru.egartech.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.ListIdsProperties;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.api.ListTaskClientImpl;

@Configuration
@ConditionalOnProperty(prefix = "clickup.list", name = "list_ids", matchIfMissing = true)
@EnableConfigurationProperties(value = ListIdsProperties.class)
@RequiredArgsConstructor
public class ListTaskClientAutoConfiguration {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Bean
    @ConditionalOnMissingBean
    public ListTaskClient listTaskClient() {
        return new ListTaskClientImpl(restTemplate, objectMapper, listIdsProperties());
    }

    public ListIdsProperties listIdsProperties() {
        return new ListIdsProperties();
    }

}
