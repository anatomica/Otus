package ru.otus.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(String s) {
        super(s);
    }

}
