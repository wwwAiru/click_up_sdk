package ru.egartech.sdk.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.egartech.sdk.exception.token.TokensNotSpecifiedException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "clickup.api")
public class AuthorizationProperties {

    private int index = 0;

    private List<String> tokens = new ArrayList<>();

    public String pullToken() {
        String pulledToken = tokens.get(index % tokens.size());
        index++;
        return pulledToken;
    }

    @PostConstruct
    public void init() {
        if (tokens.isEmpty()) {
            throw new TokensNotSpecifiedException();
        }
    }

}
