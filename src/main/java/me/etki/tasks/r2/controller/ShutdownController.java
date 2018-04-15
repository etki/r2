package me.etki.tasks.r2.controller;

import me.etki.tasks.r2.api.Acknowledgement;
import me.etki.tasks.r2.misc.LazyContainer;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Path("/v1/_shutdown")
@Produces(MediaType.APPLICATION_JSON)
public class ShutdownController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShutdownController.class);

    private final LazyContainer<Server> server;

    public ShutdownController(LazyContainer<Server> server) {
        this.server = server;
    }

    @POST
    public Acknowledgement shutdown() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.schedule(() -> {
            try {
                server.get().stop();
            } catch (Exception e) {
                LOGGER.error("Exception caught during shutdown call:", e);
            } finally {
                service.shutdown();
            }
        }, 1, TimeUnit.MILLISECONDS);
        return new Acknowledgement(true);
    }
}
