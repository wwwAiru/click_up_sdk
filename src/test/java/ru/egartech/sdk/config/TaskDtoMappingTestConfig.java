package ru.egartech.sdk.config;

import org.springframework.context.annotation.Bean;
import ru.egartech.sdk.property.FileProvider;

public class TaskDtoMappingTestConfig extends AbstractBaseConfig {
    @Bean
    public FileProvider fileProvider() {
        return new FileProvider();
    }
}
