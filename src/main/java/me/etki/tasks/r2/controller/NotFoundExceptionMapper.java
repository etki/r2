package me.etki.tasks.r2.controller;

import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException exception) {
        Problem problem = Problem
                .builder()
                .withType(Problem.DEFAULT_TYPE)
                .withTitle("Resource not found")
                .withDetail(exception.getMessage())
                .withStatus(Status.NOT_FOUND)
                .build();
        return Response
                .status(404)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(problem)
                .build();
    }
}
