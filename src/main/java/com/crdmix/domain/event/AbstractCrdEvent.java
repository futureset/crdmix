package com.crdmix.domain.event;

import org.joda.time.DateTime;

import com.crdmix.domain.eventsource.CrdEvent;

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
