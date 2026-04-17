package ru.hh.school.jetty_jersey_homework;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import ru.hh.school.jetty_jersey_homework.message.Message;
import ru.hh.school.jetty_jersey_homework.message.MessageDao;
import ru.hh.school.jetty_jersey_homework.message.MessageNotFoundException;
import ru.hh.school.jetty_jersey_homework.message.MessageSendingDto;

import java.util.Set;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/message")
@Produces(APPLICATION_JSON)
public class MessageResource {

  private final SessionFactory sessionFactory = createSessionFactory();
  private final MessageDao messageDao = new MessageDao(sessionFactory);
  private final MessageService messageService = new MessageService(messageDao, sessionFactory);

  @GET
  public Set<Message> getAllMessages() {
    return messageService.getAllMessages();
  }

  @GET
  @Path("/{id}")
  public Response getMessage(@PathParam("id") int id) {
    try {
      return Response.ok(messageService.getMessage(id)).build();
    } catch (MessageNotFoundException e) {
      return Response.status(404).build();
    }
  }

  @POST
  @Path("/send")
  @Consumes(APPLICATION_JSON)
  public Response sendMessage(MessageSendingDto messageSendingDto, @Context HttpServletRequest request) {
    if (messageSendingDto != null) {
      return Response.ok(messageService.sendMessage(messageSendingDto, request.getRemoteAddr())).build();
    }
    return Response.status(400).entity("Messages without text are not allowed").build();
  }

  private static SessionFactory createSessionFactory() {
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();
    Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(Message.class).buildMetadata();
    return metadata.buildSessionFactory();
  }

}

