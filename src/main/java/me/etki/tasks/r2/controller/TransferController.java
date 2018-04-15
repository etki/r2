package me.etki.tasks.r2.controller;

import me.etki.tasks.r2.api.Page;
import me.etki.tasks.r2.api.Transfer;
import me.etki.tasks.r2.api.TransferInput;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

@Path("/v1/transfer")
@Produces(MediaType.APPLICATION_JSON)
public class TransferController {
    @GET
    public Page<Transfer> list(
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size) {
        return new Page<>(Collections.singletonList(get(UUID.randomUUID())), Page.Pagination.from(1, 1, 1));
    }

    @GET
    @Path("/{id}")
    public Transfer get(@PathParam("id") UUID id) {
        return new Transfer(id, id, id, BigDecimal.ZERO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Transfer create(@Valid TransferInput input) {
        return new Transfer(UUID.randomUUID(), input.getSource(), input.getTarget(), input.getAmount());
    }
}
