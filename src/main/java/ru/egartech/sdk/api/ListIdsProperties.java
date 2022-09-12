package ru.egartech.sdk.api;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ListIdsProperties {

    @Value("${clickup.list.developers}")
    private Integer devs;

    @Value("${clickup.list.analytics}")
    private Integer analytics;

    @Value("${clickup.list.testers}")
    private Integer testers;


    private final List<Integer> ids = new ArrayList<>();

    @PostConstruct
    public void init() {
        ids.add(devs);
        ids.add(analytics);
        ids.add(testers);
    }

}
