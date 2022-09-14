package ru.egartech.sdk.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "clickup.search-list")
public class SearchListsProperties {

    private List<Integer> ids = new ArrayList<>();

}
