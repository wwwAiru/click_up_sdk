package ru.egartech.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.sdk.config.CustomFieldClientAutoConfiguration;
import ru.egartech.sdk.config.ListTaskClientAutoConfiguration;
import ru.egartech.sdk.config.RestTemplateAutoConfiguration;
import ru.egartech.sdk.property.FileProvider;

@SpringBootTest(classes = {
        CustomFieldClientAutoConfiguration.class,
        ListTaskClientAutoConfiguration.class,
        RestTemplateAutoConfiguration.class,
        ListTaskClientAutoConfiguration.class,
        ObjectMapper.class,
        FileProvider.class,
})
public abstract class AbstractSpringBootContext {
}
