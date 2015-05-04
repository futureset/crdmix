package com.crdmix.event.listener;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.UserFollowedUserEvent;


public interface CrdEventListener {

    default void handleMessagePosted(PostedMessageEvent postedMessageEvent) {
    }

    default void handleUserFollowed(UserFollowedUserEvent userFollowedUserEvent) {
    };

}
