package com.crdmix.event;

import com.crdmix.event.listener.CrdEventListener;

import java.time.Instant;

public class UserFollowedUserEvent extends AbstractUserCrdEvent {

    private final String followingUser;

    public UserFollowedUserEvent(Instant eventTime, String user, String followingUser) {
        super(eventTime, user);
        this.followingUser = followingUser;
    }

    public String getFollowingUser() {
        return followingUser;
    }

    @Override
    public void accept(CrdEventListener listener) {
        listener.handleUserFollowed(this);
    }

}
