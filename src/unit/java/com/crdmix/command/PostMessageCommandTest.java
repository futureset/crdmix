package com.crdmix.command;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.event.CrdEvent;
import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;
import com.crdmix.unit.config.AbstractUnitBase;

public class PostMessageCommandTest extends AbstractUnitBase<PostMessageCommand> {

    private String username = "user";
    private String message = "Hello message";
    @Mock
    private EventFactory eventFactory;
    @Mock
    private EventStore eventStore;
    @Mock
    private CrdEvent crdEvent;

    @Test
    public void executingCommandShouldGenerateAnEventAndStoreIt() {
        given(eventFactory.userPostedMessage(username, message)).willReturn(crdEvent);
        underTest.execute();
        verify(eventFactory).userPostedMessage(username, message);
        verify(eventStore).storeEvent(crdEvent);
    }

}
