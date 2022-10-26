package ru.egartech.sdk.api;

import ru.egartech.sdk.api.impl.CustomFieldClientImpl;
import ru.egartech.sdk.dto.customfield.deserialization.FieldsDto;
import ru.egartech.sdk.dto.customfield.serialization.UpdateFieldDto;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.exception.clickup.ClickUpException;

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
    FieldsDto getAccessibleCustomFields(int listId) throws ClickUpException;

    /**
     * Делает запрос в ClickUp и обновляет кастомное поле.
     *
     * @param taskId ID таски у которой нужно обновить кастомный филд.
     * @param fieldId ID кастомного филда, который нужно обновить.
     * @param updateFieldDto новое значение кастомного поля.
     * @see UpdateFieldDto
     */
    Object updateCustomFieldValue(String taskId, String fieldId, UpdateFieldDto updateFieldDto) throws ClickUpException;

    /**
     * Делает запрос в ClickUp и удаляет значение кастомного поля.
     *
     * @param taskId ID таски у которой нужно удалить кастомный филд.
     * @param fieldId ID кастомного филда, который нужно удалить.
     * @see UpdateFieldDto
     */
    void removeCustomFieldValue(String taskId, String fieldId) throws ClickUpException;
}
