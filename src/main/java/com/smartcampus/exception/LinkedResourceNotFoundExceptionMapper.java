package com.smartcampus.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    @Override
    public Response toResponse(LinkedResourceNotFoundException exception) {
        return Response.status(422)
                .entity(Map.of(
                        "status", 422,
                        "error", "Unprocessable Entity",
                        "message", exception.getMessage()
                ))
                .build();
    }
}
