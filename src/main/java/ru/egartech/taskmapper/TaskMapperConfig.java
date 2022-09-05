package ru.egartech.taskmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskMapperConfig {

    @ConditionalOnMissingBean(name = {"taskMapper"})
    @Bean
    public TaskMapper taskMapper(ObjectMapper mapper) {
        return new TaskMapper(mapper);
    }

}
