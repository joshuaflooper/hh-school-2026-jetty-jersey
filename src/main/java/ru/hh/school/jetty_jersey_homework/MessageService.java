package ru.hh.school.jetty_jersey_homework;

import ru.hh.school.jetty_jersey_homework.message.MessageDao;
import ru.hh.school.jetty_jersey_homework.message.Message;
import ru.hh.school.jetty_jersey_homework.message.MessageResponseDto;
import ru.hh.school.jetty_jersey_homework.message.MessageSendingDto;

import java.util.Set;

public class MessageService {
  private final MessageDao messageDao;

  public MessageService(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  public MessageResponseDto getMessage(int id) {
    return EntityToDto(messageDao.getById(id));
  }

  public Set<Message> getAllMessages() {
    return messageDao.getAll();
  }

  public MessageResponseDto sendMessage(MessageSendingDto messageSendingDto, String senderIp) {
    Message message = new Message();
    message.setSenderIp(senderIp);
    message.setText(messageSendingDto.text());
    messageDao.save(message);
    return EntityToDto(message);
  }

  private MessageResponseDto EntityToDto(Message message) {
    return new MessageResponseDto(
        message.getId(),
        message.getSenderIp(),
        message.getText(),
        message.getTimestamp()
    );
  }

}
