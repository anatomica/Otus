package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.LoggerProcessor;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorUpperField10;
import ru.otus.processor.homework.ProcessorExceptions;
import ru.otus.processor.homework.ProcessorExchanger;

import java.time.LocalDateTime;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
        List<Processor> processors = List.of(new ProcessorExchanger(), new ProcessorExceptions(LocalDateTime::now),
                new LoggerProcessor(new ProcessorUpperField10()));

        ComplexProcessor complexProcessor = new ComplexProcessor(processors, ex -> {});
        HistoryListener historyListener = new HistoryListener();
        complexProcessor.addListener(historyListener);

        ObjectForMessage field13 = new ObjectForMessage();
        field13.setData(List.of("field13"));

        var message = new Message.Builder(1L)
                .field11("field11")
                .field13(field13)
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(historyListener);

    }
}
