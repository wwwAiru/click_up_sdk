package ru.egartech.sdk;

import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.sdk.config.CustomFieldClientAutoConfiguration;
import ru.egartech.sdk.config.ListTaskClientAutoConfiguration;
import ru.egartech.sdk.config.RestTemplateAutoConfiguration;

@SpringBootTest(classes = {
        CustomFieldClientAutoConfiguration.class,
        ListTaskClientAutoConfiguration.class,
        RestTemplateAutoConfiguration.class,
        ListTaskClientAutoConfiguration.class})
public abstract class AbstractSpringBootContext {
}
