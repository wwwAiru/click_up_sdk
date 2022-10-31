package ru.egartech.sdk.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.AttachmentClient;
import ru.egartech.sdk.dto.attachment.deserialization.AttachmentDto;
import ru.egartech.sdk.exception.clickup.ClickUpException;
import ru.egartech.sdk.property.UrlProvider;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AttachmentClientImpl implements AttachmentClient {
    private final RestTemplate restTemplate;

    @Override
    @SneakyThrows
    public AttachmentDto createTaskAttachment(String taskId, Resource resource) {
        try {
            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("id", taskId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("attachment", resource);
            HttpEntity<?> entity = new HttpEntity<>(body, headers);
            return restTemplate.exchange(
                    UrlProvider.CREATE_TASK_ATTACHMENT.getUrl(),
                    HttpMethod.POST, entity,
                    AttachmentDto.class,
                    uriVariables).getBody();
        } catch (HttpClientErrorException e) {
            throw new ClickUpException(e);
        }
    }
}
