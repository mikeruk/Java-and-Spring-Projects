package com.example.spring20232.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object with ID was not found.")
public class WorkExpEntityNotFoundException extends RuntimeException{

    private final long entityId;

    public WorkExpEntityNotFoundException(long entityId) {
        super("Object with ID " + entityId + " not found!");
        this.entityId = entityId;
    }

    public long getEntityId() {
        return entityId;
    }

}
