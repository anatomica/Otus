package ru.otus.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    protected final Map<MoneyNominal, List<Money>> TOTAL_MONEY;

    protected final Map<MoneyNominal, List<Money>> TEMP_MONEY;

    public Storage() {
        TOTAL_MONEY = new HashMap<>();
        TEMP_MONEY = new HashMap<>();
    }

}
