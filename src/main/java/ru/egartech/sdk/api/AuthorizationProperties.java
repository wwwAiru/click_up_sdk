package ru.egartech.sdk.api;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Setter
@ConfigurationProperties(prefix = "clickup.api")
public class AuthorizationProperties {

    private int index = 0;

    private List<String> tokens;

    public String pullToken() {
        String pulledToken = tokens.get(index % tokens.size());
        index++;
        return pulledToken;
    }

}
