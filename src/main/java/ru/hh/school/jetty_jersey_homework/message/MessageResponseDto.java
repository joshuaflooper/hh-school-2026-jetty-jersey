package ru.hh.school.jetty_jersey_homework.message;

import java.time.LocalDateTime;

public record MessageResponseDto(
    Integer id,
    String senderIp,
    String text,
    LocalDateTime timestamp
) {}
