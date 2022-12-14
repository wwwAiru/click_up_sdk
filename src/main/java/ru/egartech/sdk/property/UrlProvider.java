package ru.egartech.sdk.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UrlProvider {
    BASE_URL("https://api.clickup.com/api/v2"),
    SEARCH_TASK_BY_ID_URL(BASE_URL.getUrl() + "/task/{id}?include_subtasks={include_subtasks}"),
    CREATE_TASK(BASE_URL.getUrl() + "/list/{list_id}/task"),
    UPDATE_TASK(BASE_URL.getUrl() + "/task/{id}"),
    UPDATE_CUSTOM_FIELD(BASE_URL.getUrl() + "/task/{id}/field/{field_id}"),
    GET_ACCESSIBLE_CUSTOM_FIELDS(BASE_URL.getUrl() + "/list/{list_id}/field"),
    SET_CUSTOM_FIELD_VALUE(BASE_URL.getUrl() + "/task/{task_id}/field/{field_id}"),
    DELETE_CUSTOM_FIELD_VALUE(BASE_URL.getUrl() + "/task/{task_id}/field/{field_id}"),
    DELETE_TASK(BASE_URL.getUrl() + "/task/{id}"),
    CREATE_TASK_ATTACHMENT(BASE_URL.getUrl() + "/task/{id}/attachment"),
    SEARCH_TASKS_BY_CUSTOM_FIELDS_URL(BASE_URL.getUrl() + "/list/{list_id}/task?subtasks={include_subtasks}&custom_fields={custom_field_req}");

    @Getter
    private final String url;
}
