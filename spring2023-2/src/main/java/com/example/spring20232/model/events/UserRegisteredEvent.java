package com.example.spring20232.model.events;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {

    private String username;

    public UserRegisteredEvent(Object source, String username) {
        super(source);
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
