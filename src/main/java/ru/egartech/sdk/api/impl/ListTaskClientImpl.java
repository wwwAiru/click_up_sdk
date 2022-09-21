package ru.egartech.sdk.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;
import ru.egartech.sdk.property.SearchListsProperties;

import java.util.List;

@Component
public class ListTaskClientImpl extends TaskClientImpl implements ListTaskClient {

    private final SearchListsProperties properties;

    public ListTaskClientImpl(RestTemplate restTemplate, ObjectMapper mapper, SearchListsProperties properties) {
        super(restTemplate, mapper);
        this.properties = properties;
    }

    @Override
    public List<TasksDto> getTasksByCustomFields(CustomFieldRequest<?>... customFieldRequest) {
        return getTasksByCustomFields(properties.getIds(), customFieldRequest);
    }

}
