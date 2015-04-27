package com.crdmix.domain.event;

import org.joda.time.DateTime;

import com.crdmix.domain.eventsource.CrdEventListener;

public class UserFollowedUserEvent extends AbstractUserCrdEvent {

    private final String followingUser;

    public UserFollowedUserEvent(DateTime eventTime, String user, String followingUser) {
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
