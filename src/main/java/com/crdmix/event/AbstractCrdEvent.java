package com.crdmix.event;

import java.time.Instant;

public abstract class AbstractCrdEvent implements CrdEvent {

    private final Instant eventTime;

    public AbstractCrdEvent(Instant eventTime) {
        super();
        this.eventTime = eventTime;
    }

    @Override
    public Instant getEventTime() {
        return eventTime;
    }

}
