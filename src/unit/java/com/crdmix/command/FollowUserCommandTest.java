package com.crdmix.command;

import com.crdmix.event.CrdEvent;
import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class FollowUserCommandTest extends AbstractUnitBase<FollowUserCommand> {

    private final String username = "user";
    private final String userToFollow = "followedUser";
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
