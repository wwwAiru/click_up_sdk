package ru.egartech.sdk.api;

import org.springframework.core.io.Resource;
import ru.egartech.sdk.api.impl.CustomFieldClientImpl;
import ru.egartech.sdk.dto.attachment.deserialization.AttachmentDto;
import ru.egartech.sdk.dto.customfield.serialization.UpdateFieldDto;

/**
 * Интерфейс, объявляющий методы для работы с аттачментами в ClickUp
 *
 * @see CustomFieldClientImpl
 */
public interface AttachmentClient {
    /**
     * Делает запрос в ClickUp и создаёт аттачмент к таске.
     *
     * @param taskId ID таски у которой нужно создать аттачмент.
     * @param resource Создаваемый аттачмент.
     * @see UpdateFieldDto
     */
    AttachmentDto createTaskAttachment(String taskId, Resource resource);
}
