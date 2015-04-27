package com.crdmix.domain.eventsource;

import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.domain.event.PostedMessageEvent;
import com.crdmix.domain.event.UserFollowedUserEvent;
import com.crdmix.unit.config.AbstractUnitBase;

public class CrdEventListenerTest extends AbstractUnitBase<CrdEventListener> {

    @Mock
    private UserFollowedUserEvent userFollowedUserEvent;
    @Mock
    private PostedMessageEvent postedMessageEvent;

    @Override
    protected CrdEventListener createInstance() throws Exception {
        return new CrdEventListener() {
        };
    }

    @Test
    public void hasDefaultMethodsThatDoNothing() {
        underTest.handleUserFollowed(userFollowedUserEvent);
        underTest.handleMessagePosted(postedMessageEvent);
        verifyZeroInteractions(userFollowedUserEvent, postedMessageEvent);
    }
}
