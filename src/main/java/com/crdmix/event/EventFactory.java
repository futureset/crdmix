package com.crdmix.event;


public interface EventFactory {

    CrdEvent userPostedMessage(String username, String line);

    CrdEvent userFollowedUser(String username, String followingUser);

}
