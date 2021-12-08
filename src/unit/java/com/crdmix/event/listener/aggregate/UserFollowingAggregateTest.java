package com.crdmix.event.listener.aggregate;

import com.crdmix.event.UserFollowedUserEvent;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UserFollowingAggregateTest extends AbstractUnitBase<UserFollowingAggregate> {

    private final String user = "Ben";
    @Mock
    private UserFollowedUserEvent userFollowedUserEvent;

    @Test
    public void getFollowingUsersIsInitiallyEmpty() {
        assertThat(underTest.getUserFollowingUsers(user)).isEmpty();
    }

    @Test
    public void afterFollowingUserThen() {
        String followedUser = "Alice";
        given(userFollowedUserEvent.getFollowingUser()).willReturn(followedUser);
        given(userFollowedUserEvent.getUser()).willReturn(user);
        underTest.handleUserFollowed(userFollowedUserEvent);
        assertThat(underTest.getUserFollowingUsers(user)).containsOnly(followedUser);
    }
}
