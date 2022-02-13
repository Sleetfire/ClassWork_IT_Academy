package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private static final IMessageService instance = new MessageService();
    private final List<Message> messages = new ArrayList<>();

    private MessageService() {
    }

    @Override
    public void createMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return Collections.unmodifiableList(this.messages);
    }

    @Override
    public List<Message> getMessagesBySenderLogin(String login) {
        return this.messages.stream()
                .filter(message -> Objects.equals(message.getSenderLogin(), login))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getMessagesByRecipientLogin(String login) {
        return this.messages.stream()
                .filter(message -> Objects.equals(message.getRecipientLogin(), login))
                .collect(Collectors.toList());
    }

    public static IMessageService getInstance() {
        return instance;
    }
}
