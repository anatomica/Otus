package ru.otus.print;

/**
 * Сервисный класс для печати информации
 */
public interface Printer {

    /**
     * Печать в консоли инфо о выдаче банкнот
     *
     * @param obj объект для печати
     */
    void print(Object obj);

}
