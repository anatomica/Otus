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
        return new Message(msg.getId(), msg.getField1(), msg.getField2(), msg.getField3(), msg.getField4(), msg.getField5(), msg.getField6(),
                msg.getField7(), msg.getField8(), msg.getField9(), msg.getField10(), msg.getField11(), getNewMessage(msg.getField13()))
                .toBuilder()
                .build();
    }

    private ObjectForMessage getNewMessage(ObjectForMessage field13) {
        ObjectForMessage objectForMessage = new ObjectForMessage();
        if (field13 != null) {
            List<String> fields = new ArrayList<>(field13.getData());
            objectForMessage.setData(fields);
            return objectForMessage;
        }
        return null;
    }

}
