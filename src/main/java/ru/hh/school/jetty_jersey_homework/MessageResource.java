package ru.hh.school.jetty_jersey_homework;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import ru.hh.school.jetty_jersey_homework.message.Message;
import ru.hh.school.jetty_jersey_homework.message.MessageResponseDto;
import ru.hh.school.jetty_jersey_homework.message.MessageSendingDto;

import java.util.Set;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/message")
@Produces(APPLICATION_JSON)
public class MessageResource {

  private final MessageService messageService;

  @Inject
  public MessageResource(MessageService messageService) {
    this.messageService = messageService;
  }

  @GET
  @Path("/")
  public Set<Message> getAllMessages() {
    return messageService.getAllMessages();
  }

  @GET
  @Path("/{id}")
  public MessageResponseDto getMessage(@PathParam("id") int id) {
    return messageService.getMessage(id);
  }

  @POST
  @Path("/send")
  @Consumes(APPLICATION_JSON)
  public Response sendMessage(MessageSendingDto messageSendingDto, @Context HttpServletRequest request) {
    if (messageSendingDto.text() != null) {
      return Response.ok(messageService.sendMessage(messageSendingDto, request.getRemoteAddr())).build();
    }
    return Response.status(400).entity("Messages without text are not allowed").build();
  }

}

