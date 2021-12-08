package com.crdmix.event;

import java.time.Instant;

public abstract class AbstractUserCrdEvent extends AbstractCrdEvent {

    private final String user;

    public AbstractUserCrdEvent(Instant eventTime, String user) {
        super(eventTime);
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}
