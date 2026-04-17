package ru.hh.school.jetty_jersey_homework.message;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "sender_ip",
          columnDefinition = "text NOT NULL")
  private String senderIp;

  @Column(name = "content",
          columnDefinition = "text NOT NULL")
  private String text;

  @Column(name = "send_on",
          columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
  @CreationTimestamp
  private LocalDateTime timestamp;

  public Message() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSenderIp() {
    return senderIp;
  }

  public void setSenderIp(String senderIp) {
    this.senderIp = senderIp;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
