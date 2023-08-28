package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.Processor;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class ProcessorExchanger implements Processor {

    @Override
    public Message process(Message msg) {
        if (msg != null && isNotEmpty(msg.getField13().getData())) {
            String field13 = msg.getField13().getData().get(0);
            ObjectForMessage field11 = new ObjectForMessage();
            field11.setData(List.of((String) msg.getField11()));

            return new Message(msg.getId(), msg.getField1(), msg.getField2(), msg.getField3(), msg.getField4(), msg.getField5(),
                    msg.getField6(), msg.getField7(), msg.getField8(), msg.getField9(), msg.getField10(), field13, field11)
                    .toBuilder()
                    .build();
        }

        return null;
    }

}
