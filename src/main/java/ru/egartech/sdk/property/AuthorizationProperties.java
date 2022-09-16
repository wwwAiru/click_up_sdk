package ru.egartech.sdk.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "clickup.api")
@Validated
public class AuthorizationProperties {
    private int index = 0;

    @NotEmpty
    private List<String> tokens = new ArrayList<>();

    public String pullToken() {
        String pulledToken = tokens.get(index % tokens.size());
        index++;
        return pulledToken;
    }
}
