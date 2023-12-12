package com.example.spring20232.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object with ID was not found.")
public class EducationEntityNotFoundException extends RuntimeException {

    private final long entityId;


    public EducationEntityNotFoundException(long entityId) {
        super("Object with ID " + entityId + " not found!");
        this.entityId = entityId;
    }


    public long getEntityId() {
        return entityId;
    }
}
