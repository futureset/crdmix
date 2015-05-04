package com.crdmix.event;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.crdmix.event.CrdEvent;
import com.crdmix.event.NotifyingEventStore;
import com.crdmix.event.listener.CrdEventListener;
import com.crdmix.unit.config.AbstractUnitBase;

public class NotifyingEventStoreTest extends AbstractUnitBase<NotifyingEventStore> {

    @Mock
    private CrdEventListener listener1;
    @Mock
    private CrdEventListener listener2;
    @Mock
    private CrdEvent event;

    @Test
    public void storingAnEventNotifiesListeners() {
        underTest.registerListener(listener1);
        underTest.registerListener(listener2);
        underTest.storeEvent(event);
        verify(event).accept(listener1);
        Mockito.verify(event).accept(listener2);

    }
}
