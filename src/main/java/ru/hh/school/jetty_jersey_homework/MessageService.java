package ru.hh.school.jetty_jersey_homework;

import org.hibernate.SessionFactory;
import ru.hh.school.jetty_jersey_homework.message.*;

import java.util.Optional;
import java.util.Set;

public class MessageService {
  private final MessageDao messageDao;
  private final SessionFactory sessionFactory;
  private final TransactionHelper transactionHelper;

  public MessageService(MessageDao messageDao, SessionFactory sessionFactory) {
    this.messageDao = messageDao;
    this.sessionFactory = sessionFactory;
    transactionHelper = new TransactionHelper(sessionFactory);
  }

  public MessageResponseDto getMessage(int id) throws MessageNotFoundException {
    Optional<Message> optionalMessage = transactionHelper.inTransaction(() -> messageDao.getById(id));
    if (optionalMessage.isEmpty()) {
      throw new MessageNotFoundException();
    }
    return EntityToDto(optionalMessage.get());
  }

  public Set<Message> getAllMessages() {
    return transactionHelper.inTransaction(messageDao::getAll);
  }

  public MessageResponseDto sendMessage(MessageSendingDto messageSendingDto, String senderIp) {
    Message message = new Message();
    message.setSenderIp(senderIp);
    message.setText(messageSendingDto.text());
    transactionHelper.inTransaction(() -> messageDao.save(message));
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
