package ru.egartech.sdk.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.dto.customfield.deserialization.FieldsDto;
import ru.egartech.sdk.dto.customfield.serialization.UpdateFieldDto;
import ru.egartech.sdk.exception.clickup.ClickUpException;
import ru.egartech.sdk.property.UrlProvider;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomFieldClientImpl implements CustomFieldClient {
    private final RestTemplate restTemplate;

    @Override
    public FieldsDto getAccessibleCustomFields(int listId) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("list_id", listId);
        try {
            return restTemplate.getForObject(
                    UrlProvider.GET_ACCESSIBLE_CUSTOM_FIELDS.getUrl(),
                    FieldsDto.class,
                    uriVariables);
        } catch (HttpClientErrorException e) {
            throw new ClickUpException(e);
        }
    }

    @Override
    public Object updateCustomFieldValue(String taskId, String fieldId, UpdateFieldDto updateFieldDto) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("task_id", taskId);
        uriVariables.put("field_id", fieldId);
        try {
            return restTemplate.postForObject(
                    UrlProvider.SET_CUSTOM_FIELD_VALUE.getUrl(),
                    updateFieldDto,
                    Object.class,
                    uriVariables);
        } catch (HttpClientErrorException e) {
            throw new ClickUpException(e);
        }
    }

    @Override
    public void removeCustomFieldValue(String taskId, String fieldId) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("task_id", taskId);
        uriVariables.put("field_id", fieldId);
        try {
            restTemplate.delete(UrlProvider.DELETE_CUSTOM_FIELD_VALUE.getUrl(), uriVariables);
        } catch (HttpClientErrorException e) {
            throw new ClickUpException(e);
        }
    }
}
