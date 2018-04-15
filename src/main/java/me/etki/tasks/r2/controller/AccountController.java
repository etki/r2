package me.etki.tasks.r2.controller;

import me.etki.tasks.r2.api.AccountInput;
import me.etki.tasks.r2.api.Page;
import me.etki.tasks.r2.api.Account;

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

@Path("/v1/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {
    @GET
    public Page<Account> list(
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size) {

        return new Page<>(Collections.singletonList(get(UUID.randomUUID())), Page.Pagination.from(1, 1, 1));
    }

    @GET
    @Path("/{id}")
    public Account get(@PathParam("id") UUID id) {
        return new Account(id, BigDecimal.ZERO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Account create(@Valid AccountInput input) {
        return new Account(UUID.randomUUID(), input.getBalance());
    }
}
