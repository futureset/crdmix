package com.crdmix.event;

import java.util.LinkedHashSet;
import java.util.Set;

import com.crdmix.event.listener.CrdEventListener;

public class NotifyingEventStore implements EventStore {

    private final Set<CrdEventListener> eventListeners = new LinkedHashSet<>();

    @Override
    public void storeEvent(CrdEvent event) {
        eventListeners.forEach(listener -> event.accept(listener));
    }

    public void registerListener(CrdEventListener listener) {
        eventListeners.add(listener);
    }
}
