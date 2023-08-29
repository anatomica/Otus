package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorExceptions implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorExceptions(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        int seconds = dateTimeProvider.getDateTime().getSecond();
        if (seconds % 2 == 0) {
            throw new ProcessorException("ProcessorException");
        }
        return message;
    }

    public static class ProcessorException extends RuntimeException {
        public ProcessorException(String message) {
            super(message);
        }
    }

}
