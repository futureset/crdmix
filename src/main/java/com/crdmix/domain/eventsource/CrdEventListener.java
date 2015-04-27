package com.crdmix.domain.eventsource;

import com.crdmix.domain.event.PostedMessageEvent;
import com.crdmix.domain.event.UserFollowedUserEvent;

public interface CrdEventListener {

    default void handleMessagePosted(PostedMessageEvent postedMessageEvent) {
    }

    default void handleUserFollowed(UserFollowedUserEvent userFollowedUserEvent) {
    };

}
