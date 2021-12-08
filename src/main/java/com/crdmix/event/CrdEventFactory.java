package com.crdmix.event;

import java.time.Clock;
import java.time.Instant;

public class CrdEventFactory implements EventFactory {

    private final Clock clock;

    public CrdEventFactory(Clock clock) {
        this.clock = clock;
    }

    @Override
    public PostedMessageEvent userPostedMessage(String username, String message) {
        return new PostedMessageEvent(getCurrentDateTime(), username, message);
    }

    private Instant getCurrentDateTime() {
        return Instant.now(clock);
    }

    @Override
    public UserFollowedUserEvent userFollowedUser(String user, String followingUser) {
        return new UserFollowedUserEvent(getCurrentDateTime(), user, followingUser);
    }

}
