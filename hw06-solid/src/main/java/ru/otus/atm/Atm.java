package ru.otus.atm;

import ru.otus.money.Money;

import java.util.List;

/**
 * Интерфейс банкомата
 */
public interface Atm {

    /**
     * Добавить купюры в банкомат
     *
     * @param moneys перечисление купюр одной строкой
     */
    void addMoney(String moneys);

    /**
     * Получить запрошенную сумму из Банкомата.
     * При условии невозможности выдать запрошенную сумму - выкидывается исключение
     *
     * @param moneys Запрошенная сумма
     * @return List<Money> список банкнот
     */
    List<Money> getMoney(String moneys);

    /**
     * Получить всю сумму остатка на балансе счета
     *
     * @return List<Money> список банкнот
     */
    List<Money> getTotal();

}
