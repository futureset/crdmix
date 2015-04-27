package com.crdmix.domain.eventsource;

public interface EventStore {

    void storeEvent(CrdEvent event);
}
