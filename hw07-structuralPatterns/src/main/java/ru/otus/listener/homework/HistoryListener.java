package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class HistoryListener implements Listener, HistoryReader {

    private final List<Message> messages = new ArrayList<>();

    @Override
    public void onUpdated(Message msg) {
        messages.add(getNewMessage(msg));
        var logString = String.format("oldMsg:%s", msg);
        System.out.println(logString);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        if (isNotEmpty(messages)) {
            return messages.stream().filter(msg -> msg.getId() == id).findFirst();
        }

        throw new UnsupportedOperationException();
    }

    private Message getNewMessage(Message msg) {
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
                .field11(msg.getField11())
                .field13(getNewMessage(msg.getField13()))
                .build();
    }

    private ObjectForMessage getNewMessage(ObjectForMessage field13) {
        ObjectForMessage objectForMessage = new ObjectForMessage();
        List<String> fields = new ArrayList<>(field13.getData());
        objectForMessage.setData(fields);
        return objectForMessage;
    }

}
