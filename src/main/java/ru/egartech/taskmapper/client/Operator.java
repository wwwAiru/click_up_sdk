package ru.egartech.taskmapper.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Operator {
    EQUALS("="),
    ;

    private final String operator;
}
