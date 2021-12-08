package com.crdmix.event;

import com.crdmix.event.listener.CrdEventListener;

import java.time.Instant;

public interface CrdEvent {

    Instant getEventTime();

    void accept(CrdEventListener listener);
}
