package ru.egartech.taskmapper.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UrlProvider {

    BASE_URL("https://api.clickup.com/api/v2"),
    SEARCH_TASK_BY_ID_URL("/task/{id}?include_subtasks={include_subtasks}"),
    CREATE_TASK("https://api.clickup.com/api/v2/list/{list_id}/task"),
    SEARCH_TASKS_BY_CUSTOM_FIELDS_URL("/list/{list_id}/task?custom_fields={custom_field_req}");

    @Getter
    private final String url;

}
