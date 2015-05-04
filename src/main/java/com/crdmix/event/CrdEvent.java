package com.crdmix.event;

import org.joda.time.DateTime;

import com.crdmix.event.listener.CrdEventListener;

public interface CrdEvent {

    DateTime getEventTime();

    void accept(CrdEventListener listener);
}
