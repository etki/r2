package me.etki.tasks.r2.controller;

import me.etki.tasks.r2.api.ApplicationStatus;
import me.etki.tasks.r2.api.HealthColor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/_health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthController {
    @GET
    public ApplicationStatus index() {
        return new ApplicationStatus(HealthColor.GREEN);
    }
}
