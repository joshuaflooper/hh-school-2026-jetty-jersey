package ru.hh.school.jetty_jersey_homework;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces(APPLICATION_JSON)
public class MainResource {

  @GET
  public String helloMessage() {
    return "Welcome to DumbChat!";
  }

}
