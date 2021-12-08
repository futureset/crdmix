package com.crdmix.event;

import com.crdmix.event.listener.CrdEventListener;

import java.time.Instant;

public class PostedMessageEvent extends AbstractUserCrdEvent {

    private final String message;

    public PostedMessageEvent(Instant eventTime, String user, String message) {
        super(eventTime, user);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void accept(CrdEventListener listener) {
        listener.handleMessagePosted(this);

    }

}
