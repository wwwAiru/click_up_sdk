package ru.egartech.taskmapper.api;

import ru.egartech.taskmapper.dto.task.*;

/**
 * Интерфейс, объявляющий методы для работы с тасками в ClickUp. Все методы, кроме <b>updateTask()</b>, в точности повторяют собственное API ClickUp, <b>updateTask()</b> имеет более сложную внутреннюю логику, необходимо прочитать документацию к этому методу прежде чем использовать.
 *
 * @see TaskClientImpl
 */
public interface TaskClient {

    /**
     * Делает запрос в ClickUp и возвращает {@link TaskDto} по Task ID.
     *
     * @param id идентификатор таски в ClickUp.
     * @param includeSubtasks если <b>True</b>, то возвратить сабтаски.
     *
     *
     * @see TaskDto*/
    TaskDto getTaskById(String id, Boolean includeSubtasks);

    /**
     * Делает запрос в ClickUp и возвращает {@link TaskDto} по {@link CustomFieldRequest}.
     *
     * @param listId идентификатор листа, в котором находится задача.
     * @param customFieldRequest объект для поиска по кастом филду, состоящий из: идентификатора поля,
     * оператора сравнения и значения поля.
     *
     * @see CustomFieldRequest */
    TasksDto getTasksByCustomFields(int listId, CustomFieldRequest<?>... customFieldRequest);

    /**
     * Делает запрос в ClickUp и создаёт новую таску, возвращает {@link TaskDto} (только что созданная таска).
     *
     * @param listId идентификатор листа, в котором нужно создать задачу.
     * @param createTaskDto объект, на основе не null полей которого будет создаваться таска.
     *
     * @see CreateTaskDto*/
    TaskDto createTask(int listId, RequestTaskDto createTaskDto);

    /**
     * <p><b>Опасно!</b></p>
     *
     * Делает несколько запросов в кликап, для каскадного обновления таски, притом количество запросов: <b>2+N</b> где N - количество кастомных филдов, указанных в {@link UpdateTaskDto}. Запрос может занять какое-то время или исчерпать лимит запосов в кликап (100 в минуту для каждого Free токена).
     *
     * @param updateTaskDto - объект, на основе не null полей которого будет обновляться таска (остальные поля будут игнорироваться).
     * @see UpdateTaskDto
     * */
    TaskDto updateTask(RequestTaskDto updateTaskDto);

}
