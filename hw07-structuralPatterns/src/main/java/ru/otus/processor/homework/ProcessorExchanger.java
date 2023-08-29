package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.Processor;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class ProcessorExchanger implements Processor {

    @Override
    public Message process(Message message) {
        if (message != null && isNotEmpty(message.getField13().getData())) {
            String field13 = message.getField13().getData().get(0);
            ObjectForMessage field11 = new ObjectForMessage();
            field11.setData(List.of((String) message.getField11()));

            return message.toBuilder().field11(field13).field13(field11).build();
        }

        return null;
    }

}
