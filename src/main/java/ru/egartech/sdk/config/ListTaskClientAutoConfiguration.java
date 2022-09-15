package ru.egartech.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.property.SearchListsProperties;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.api.impl.ListTaskClientImpl;

@Configuration
@ConditionalOnProperty(prefix = "clickup.search-list", name = "list_ids", matchIfMissing = true)
@EnableConfigurationProperties(value = SearchListsProperties.class)
@RequiredArgsConstructor
public class ListTaskClientAutoConfiguration {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final SearchListsProperties searchListsProperties;

    @Bean
    @ConditionalOnMissingBean
    public ListTaskClient listTaskClient() {
        return new ListTaskClientImpl(restTemplate, objectMapper, searchListsProperties);
    }

}
