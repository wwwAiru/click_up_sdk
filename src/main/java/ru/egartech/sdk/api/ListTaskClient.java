package ru.egartech.sdk.api;

import ru.egartech.sdk.dto.task.CustomFieldRequest;
import ru.egartech.sdk.dto.task.TaskDto;
import ru.egartech.sdk.dto.task.TasksDto;

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
     *
     * Делает несколько запросов в несколько списков ClickUp и возвращает {@link TaskDto} по {@link CustomFieldRequest}, притом количество запросов: <b>N</b>, где N - количество списокв, в которых может находиться задача.
     *
     * @param customFieldRequest объект для поиска по кастом филду, состоящий из: идентификатора поля,
     *                           оператора сравнения и значения поля.
     * @see CustomFieldRequest
     */
    List<TasksDto> getTasksByCustomFields(CustomFieldRequest<?>... customFieldRequest);

}
