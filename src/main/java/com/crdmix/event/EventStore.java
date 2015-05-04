package com.crdmix.event;


public interface EventStore {

    void storeEvent(CrdEvent event);
}
