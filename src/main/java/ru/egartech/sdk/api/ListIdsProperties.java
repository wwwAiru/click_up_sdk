package ru.egartech.sdk.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "clickup.list")
public class ListIdsProperties {

    private Integer devs;

    private Integer analytics;

    private Integer testers;

    private final List<Integer> ids = new ArrayList<>();

    @PostConstruct
    public void init() {
        ids.add(devs);
        ids.add(analytics);
        ids.add(testers);
    }

}
