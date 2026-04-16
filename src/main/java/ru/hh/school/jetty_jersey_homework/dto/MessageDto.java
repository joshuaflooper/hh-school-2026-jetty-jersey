package ru.hh.school.jetty_jersey_homework.dto;

import java.time.LocalDateTime;

public record MessageDto(Integer id, String senderIp, String text, LocalDateTime time) {
}
