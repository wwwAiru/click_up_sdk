package ru.egartech.sdk.api;

import ru.egartech.sdk.api.impl.ListTaskClientImpl;
import ru.egartech.sdk.api.impl.TaskClientImpl;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;

import java.util.List;

/**
 * Интерфейс, объявляющий методы для работы с тасками в ClickUp, с преждевременным объявлением всех listIds для поиска. Документация {@link TaskClient} актуальна.
 *
 * @see ListTaskClientImpl
 * @see TaskClient
 * @see TaskClientImpl
 */
public interface ListTaskClient extends TaskClient {

    /**
     * <p><b>Опасно!</b></p>
     * <p>
     * Делает несколько запросов в несколько списков ClickUp и возвращает {@link TaskDto} по {@link CustomFieldRequest}, притом количество запросов: <b>N</b>, где N - количество списокв, в которых может находиться задача.
     *
     * @param customFieldRequest объект для поиска по кастом филду, состоящий из: идентификатора поля,
     *                           оператора сравнения и значения поля.
     * @see CustomFieldRequest
     */
    List<TasksDto> getTasksByCustomFields(Boolean includeSubtasks, CustomFieldRequest<?>... customFieldRequest);

}
