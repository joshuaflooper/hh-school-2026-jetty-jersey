package ru.hh.school.jetty_jersey_homework;

import jakarta.ws.rs.ext.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.WebApplicationException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Provider
public class RequestLoggingInterceptor implements ReaderInterceptor {

  final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
    String body = new BufferedReader(new InputStreamReader(context.getInputStream()))
        .lines()
        .collect(Collectors.joining("\n"));
    logger.info("{} - Incoming request with body.\nHeaders: {}\nBody: {}", LocalDateTime.now(), context.getHeaders(), body);
    context.setInputStream(new ByteArrayInputStream(body.getBytes()));
    return context.proceed();
  }
}