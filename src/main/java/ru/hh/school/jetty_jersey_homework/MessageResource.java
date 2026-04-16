package ru.hh.school.jetty_jersey_homework;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import ru.hh.school.jetty_jersey_homework.dto.MessageDto;
import ru.hh.school.jetty_jersey_homework.dto.MessageSendingDto;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/message")
@Produces(APPLICATION_JSON)
public class MessageResource {

  private final MessageService messageService = new MessageService();

  @GET
  @Path("/")
  public List<MessageDto> getAllMessages() {
    return messageService.getAllMessages();
  }

  @GET
  @Path("/{id}")
  public MessageDto getMessage(@PathParam("id") int id) {
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

