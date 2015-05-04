package com.crdmix.event.listener;

import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.UserFollowedUserEvent;
import com.crdmix.event.listener.CrdEventListener;
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
