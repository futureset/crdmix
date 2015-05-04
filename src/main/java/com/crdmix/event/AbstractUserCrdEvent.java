package com.crdmix.event;

import org.joda.time.DateTime;

public abstract class AbstractUserCrdEvent extends AbstractCrdEvent {

    private final String user;

    public AbstractUserCrdEvent(DateTime eventTime, String user) {
        super(eventTime);
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}
