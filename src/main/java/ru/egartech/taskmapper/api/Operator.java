package ru.egartech.taskmapper.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Operator {

    EQUALS("=");

    @Getter
    private final String operator;
}
