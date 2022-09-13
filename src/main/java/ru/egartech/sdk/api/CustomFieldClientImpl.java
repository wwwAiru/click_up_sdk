package ru.egartech.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egartech.sdk.dto.task.FieldsDto;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomFieldClientImpl implements CustomFieldClient {

    private final RestTemplate restTemplate;

    @Override
    public FieldsDto getAccessibleCustomFields(int listId) {

        Map<String, Object> uriVariables = new HashMap<>();

        uriVariables.put("list_id", listId);

        return restTemplate.getForObject(
                UrlProvider.GET_ACCESSIBLE_CUSTOM_FIELDS.getUrl(),
                FieldsDto.class,
                uriVariables);
    }

}
