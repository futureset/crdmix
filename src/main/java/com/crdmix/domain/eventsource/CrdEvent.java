package com.crdmix.domain.eventsource;

import org.joda.time.DateTime;

public interface CrdEvent {

    DateTime getEventTime();

    void accept(CrdEventListener listener);
}
