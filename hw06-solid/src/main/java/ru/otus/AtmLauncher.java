package ru.otus;

import ru.otus.atm.Atm;
import ru.otus.atm.AtmImpl;
import ru.otus.exceptions.NotEnoughMoneyException;
import ru.otus.money.Money;
import ru.otus.print.Printer;
import ru.otus.print.PrinterImpl;

import java.util.List;

public class AtmLauncher {

    public static void main(String[] args) {
        Printer printer = new PrinterImpl();
        Atm atm = new AtmImpl();
        atm.addMoney("10, 10, 10, 50, 50, 100, 100, 500, 1000");

        try {
            List<Money> takeOne = atm.getMoney("70");
            printer.print(takeOne);
        } catch (NotEnoughMoneyException e) {
            printer.print(e.getMessage());
        }

        try {
            List<Money> takeTwo = atm.getMoney("35");
            printer.print(takeTwo);
        } catch (NotEnoughMoneyException e) {
            printer.print(e.getMessage());
        }

        try {
            List<Money> takeThird = atm.getTotal();
            printer.print(takeThird);
        } catch (NotEnoughMoneyException e) {
            printer.print(e.getMessage());
        }
    }

}
