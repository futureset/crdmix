package com.crdmix.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.event.UserFollowedUserEvent;
import com.crdmix.event.listener.CrdEventListener;
import com.crdmix.unit.config.AbstractUnitBase;
import com.crdmix.unit.config.UnitUtil;

public class UserFollowedUserEventTest extends AbstractUnitBase<UserFollowedUserEvent> {

    private DateTime dateTime = new DateTime(0L);
    private String user = "ben";
    private String followingUser = "alice";
    @Mock
    private CrdEventListener listener;

    @Test
    public void checkMessagePopulatedCorrectlyFromParameters() {
        assertThat(underTest.getUser()).isEqualTo(user);
        assertThat(underTest.getFollowingUser()).isEqualTo(followingUser);
        assertThat(underTest.getEventTime()).isEqualTo(dateTime);
    }

    @Test
    public void checkImmutable() {
        assertThat(UnitUtil.mutableFields(clazz)).isEmpty();
    }

    @Test
    public void acceptMethodMakesTheWriteCallBackToTheEventListener() {
        underTest.accept(listener);
        verify(listener).handleUserFollowed(underTest);
    }

}
