package ru.egartech.sdk.api;

import ru.egartech.sdk.api.impl.CustomFieldClientImpl;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.FieldsDto;

/**
 * Интерфейс, объявляющий методы для работы с кастомными филдами в ClickUp
 *
 * @see CustomFieldClientImpl
 */
public interface CustomFieldClient {

    /**
     * Делает запрос в ClickUp и возвращает {@link FieldsDto} по List ID.
     *
     * @param listId идентификатор списка для поиска кастомных филдов.
     * @see TaskDto
     */
    FieldsDto getAccessibleCustomFields(int listId);

}
