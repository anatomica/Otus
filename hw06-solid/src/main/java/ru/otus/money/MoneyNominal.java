package ru.otus.money;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum MoneyNominal {

    TEN(10),

    FIFTY(50),

    HUNDRED(100),

    FIVE_HUNDRED(500),

    THOUSAND(1000);

    private final int value;

    MoneyNominal(Integer value) {
        this.value = value;
    }

    public static MoneyNominal of(Integer type) {
        return Stream.of(MoneyNominal.values())
                .filter(p -> p.getValue() == type)
                .findFirst().orElse(null);
    }

}
