package ru.hh.school.jetty_jersey_homework.message;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageDao {

  private final SessionFactory sessionFactory;

  public MessageDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void save(Message message) {
    session().persist(message);
  }

  public Optional<Message> getById(int id) {
    return Optional.ofNullable(session().find(Message.class, id));
  }

  public Set<Message> getAll() {
    return session().createQuery("select from messages m", Message.class)
        .stream()
        .collect(Collectors.toSet());
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }

}
