package com.crdmix.command;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.domain.eventsource.CrdEvent;
import com.crdmix.domain.eventsource.EventFactory;
import com.crdmix.domain.eventsource.EventStore;
import com.crdmix.unit.config.AbstractUnitBase;

public class FollowUserCommandTest extends AbstractUnitBase<FollowUserCommand> {

    private String username = "user";
    private String userToFollow = "followedUser";
    @Mock
    private EventFactory eventFactory;
    @Mock
    private EventStore eventStore;
    @Mock
    private CrdEvent crdEvent;

    @Test
    public void executingCommandShouldGenerateAnEventAndStoreIt() {
        given(eventFactory.userFollowedUser(username, userToFollow)).willReturn(crdEvent);

        underTest.execute();

        verify(eventFactory).userFollowedUser(username, userToFollow);
        verify(eventStore).storeEvent(crdEvent);
    }

}
