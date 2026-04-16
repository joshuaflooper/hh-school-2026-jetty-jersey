package ru.hh.school.jetty_jersey_homework;

import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

  static void main() throws Exception {
    Server server = new Server(8080);
    ServletContextHandler context = new ServletContextHandler("/");
    server.setHandler(context);

    ResourceConfig config = new ResourceConfig();
    config.register(MainResource.class);
    config.register(MessageResource.class);

    ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));

    context.addServlet(servletHolder, "/*");

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

}
