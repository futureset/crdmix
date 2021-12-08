package com.crdmix.event;

import com.crdmix.event.listener.CrdEventListener;
import com.crdmix.unit.config.AbstractUnitBase;
import com.crdmix.unit.config.UnitUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class UserFollowedUserEventTest extends AbstractUnitBase<UserFollowedUserEvent> {

    private final Instant dateTime = Instant.now();
    private final String user = "ben";
    private final String followingUser = "alice";
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
