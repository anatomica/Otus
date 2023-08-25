package ru.otus.processor;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class ProcessorExchanger implements Processor {

    @Override
    public Message process(Message msg) {
        if (msg != null && isNotEmpty(msg.getField13().getData())) {
            String field13 = msg.getField13().getData().get(0);
            ObjectForMessage field11 = new ObjectForMessage();
            field11.setData(List.of((String) msg.getField11()));

            return new Message.Builder(msg.getId())
                    .field1(msg.getField1())
                    .field2(msg.getField2())
                    .field3(msg.getField3())
                    .field4(msg.getField4())
                    .field5(msg.getField5())
                    .field6(msg.getField6())
                    .field7(msg.getField7())
                    .field8(msg.getField8())
                    .field9(msg.getField9())
                    .field10(msg.getField10())
                    .field11(field13)
                    .field13(field11)
                    .build();
        }

        return null;
    }

}
