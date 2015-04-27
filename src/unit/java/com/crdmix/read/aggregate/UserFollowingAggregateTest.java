package com.crdmix.read.aggregate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.domain.event.UserFollowedUserEvent;
import com.crdmix.unit.config.AbstractUnitBase;

public class UserFollowingAggregateTest extends AbstractUnitBase<UserFollowingAggregate> {

    private String user = "Ben";
    @Mock
    private UserFollowedUserEvent userFollowedUserEvent;
    private String followedUser = "Alice";

    @Test
    public void getFollowingUsersIsInitiallyEmpty() {
        assertThat(underTest.getUserFollowingUsers(user)).isEmpty();
    }

    @Test
    public void afterFollowingUserThen() {
        given(userFollowedUserEvent.getFollowingUser()).willReturn(followedUser);
        given(userFollowedUserEvent.getUser()).willReturn(user);
        underTest.handleUserFollowed(userFollowedUserEvent);
        assertThat(underTest.getUserFollowingUsers(user)).containsOnly(followedUser);
    }
}
