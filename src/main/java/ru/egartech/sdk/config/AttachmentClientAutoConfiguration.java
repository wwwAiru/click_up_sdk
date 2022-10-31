package ru.egartech.sdk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.AttachmentClient;
import ru.egartech.sdk.api.impl.AttachmentClientImpl;
import ru.egartech.sdk.dto.attachment.serialization.ByteArrayResourceSerializer;

@Configuration
@Import(ByteArrayResourceSerializer.class)
@ConditionalOnClass(AttachmentClient.class)
@RequiredArgsConstructor
public class AttachmentClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AttachmentClient attachmentClient(RestTemplate restTemplate) {
        return new AttachmentClientImpl(restTemplate);
    }
}
