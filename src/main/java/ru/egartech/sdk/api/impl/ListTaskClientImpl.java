package ru.egartech.sdk.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;
import ru.egartech.sdk.exception.clickup.ClickUpException;
import ru.egartech.sdk.property.SearchListsProperties;

import java.util.List;

public class ListTaskClientImpl extends TaskClientImpl implements ListTaskClient {
    private final SearchListsProperties properties;

    public ListTaskClientImpl(CustomFieldClient customFieldClient,
                              RestTemplate restTemplate,
                              ObjectMapper mapper,
                              SearchListsProperties properties) {
        super(customFieldClient, restTemplate, mapper);
        this.properties = properties;
    }

    @Override
    public List<TasksDto> getTasksByCustomFields(Boolean includeSubtasks, CustomFieldRequest<?>... customFieldRequest) {
        try {
            return getTasksByCustomFields(properties.getIds(), includeSubtasks, customFieldRequest);
        } catch (HttpClientErrorException e) {
            throw new ClickUpException(e);
        }
    }
}
