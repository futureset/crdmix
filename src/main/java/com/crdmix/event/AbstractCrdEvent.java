package com.crdmix.event;

import org.joda.time.DateTime;

public abstract class AbstractCrdEvent implements CrdEvent {

    private final DateTime eventTime;

    public AbstractCrdEvent(DateTime eventTime) {
        super();
        this.eventTime = eventTime;
    }

    @Override
    public DateTime getEventTime() {
        return eventTime;
    }

}
