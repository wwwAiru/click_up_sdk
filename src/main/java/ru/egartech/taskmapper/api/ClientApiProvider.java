package ru.egartech.taskmapper.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientApiProvider {

    private final TaskClient taskClient;

    public TaskClient getTaskClient() {
        return taskClient;
    }

}
