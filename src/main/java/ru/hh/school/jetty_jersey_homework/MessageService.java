package ru.hh.school.jetty_jersey_homework;

import ru.hh.school.jetty_jersey_homework.dao.MessageDao;
import ru.hh.school.jetty_jersey_homework.dto.MessageDto;
import ru.hh.school.jetty_jersey_homework.dto.MessageSendingDto;

import java.util.Set;

public class MessageService {
  private final MessageDao messageDao = new MessageDao();

  public MessageDto getMessage(int id) {
    return messageDao.getById(id);
  }

  public Set<MessageDto> getAllMessages() {
    return messageDao.getAll();
  }

  public MessageDto sendMessage(MessageSendingDto messageSendingDto, String senderIp) {
    MessageDto message = new MessageDto(null, messageSendingDto.text(), senderIp, null);
    messageDao.save(message);
    return message;
  }
}
