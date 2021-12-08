package com.crdmix.event.listener;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.UserFollowedUserEvent;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verifyNoInteractions;

public class CrdEventListenerTest extends AbstractUnitBase<CrdEventListener> {

    @Mock
    private UserFollowedUserEvent userFollowedUserEvent;
    @Mock
    private PostedMessageEvent postedMessageEvent;

    @Override
    protected CrdEventListener createInstance() {
        return new CrdEventListener() {
        };
    }

    @Test
    public void hasDefaultMethodsThatDoNothing() {
        underTest.handleUserFollowed(userFollowedUserEvent);
        underTest.handleMessagePosted(postedMessageEvent);
        verifyNoInteractions(userFollowedUserEvent, postedMessageEvent);
    }
}
