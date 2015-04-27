package com.crdmix.domain.event;

import org.joda.time.DateTime;

import com.crdmix.domain.eventsource.CrdEventListener;

public class PostedMessageEvent extends AbstractUserCrdEvent {

    private final String message;

    public PostedMessageEvent(DateTime eventTime, String user, String message) {
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
