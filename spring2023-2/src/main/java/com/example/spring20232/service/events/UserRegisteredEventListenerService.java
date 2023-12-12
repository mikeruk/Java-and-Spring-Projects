package com.example.spring20232.service.events;

import com.example.spring20232.model.events.UserRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserRegisteredEventListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventListenerService.class);

    private boolean eventWasTriggered = false;
    private String username;

    @EventListener(UserRegisteredEvent.class)
    public void onUserRegisteredEvent(UserRegisteredEvent userRegisteredEvent) {
        //long timestamp = evt.getTimestamp();
        this.username = userRegisteredEvent.getUsername();
        this.eventWasTriggered = true;

    }

    @Scheduled(fixedDelay = 5000)
    public void checkForRegisteredUser() {
        if (this.eventWasTriggered == true) {
            LOGGER.info(String.format("NEW USER WITH USERNAME %s WAS REGISTERED !!!", this.username));
        }
        this.eventWasTriggered = false;
    }

}
