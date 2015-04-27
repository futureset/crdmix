package com.crdmix.domain.event;

import org.joda.time.DateTime;

import com.crdmix.domain.eventsource.EventFactory;

public class CrdEventFactory implements EventFactory {

    @Override
    public PostedMessageEvent userPostedMessage(String username, String message) {
        return new PostedMessageEvent(getCurrentDateTime(), username, message);
    }

    private DateTime getCurrentDateTime() {
        return new DateTime();
    }

    @Override
    public UserFollowedUserEvent userFollowedUser(String user, String followingUser) {
        return new UserFollowedUserEvent(getCurrentDateTime(), user, followingUser);
    }

}
