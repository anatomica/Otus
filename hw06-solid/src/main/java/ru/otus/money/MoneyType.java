package ru.otus.money;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum MoneyType {

    TEN(10),

    FIFTY(50),

    HUNDRED(100),

    FIVE_HUNDRED(500),

    THOUSAND(1000);

    private final Integer value;

    MoneyType(Integer value) {
        this.value = value;
    }

    public static MoneyType of(Integer type) {
        return Stream.of(MoneyType.values())
                .filter(p -> p.getValue().equals(type))
                .findFirst().orElse(null);
    }

}
