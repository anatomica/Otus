package ru.otus.print;

import ru.otus.money.Money;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class PrinterImpl implements Printer {

    public void print(Object obj) {
        String message = EMPTY;
        if (obj instanceof ArrayList<?> array) {
            if (array.get(0) instanceof Money) {
                List<Integer> thisValues = array.stream().map(m -> ((Money) m).getValue()).toList();
                message = String.format("%nBanknotes received: %s", thisValues);
            }
        }
        if (obj instanceof String str) {
            message = String.format("%nError while getting: %s", str);
        }
        System.out.println(message);
    }

}
