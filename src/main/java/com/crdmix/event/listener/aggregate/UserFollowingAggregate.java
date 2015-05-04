package com.crdmix.event.listener.aggregate;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.crdmix.event.UserFollowedUserEvent;
import com.crdmix.event.listener.CrdEventListener;

public class UserFollowingAggregate implements CrdEventListener {

    private final Map<String, Set<String>> userFollowingUsers;

    public UserFollowingAggregate() {
        super();
        userFollowingUsers = new HashMap<>();
    }

    @Override
    public void handleUserFollowed(UserFollowedUserEvent userFollowedUserEvent) {
        Set<String> followedUsers = userFollowingUsers.computeIfAbsent(userFollowedUserEvent.getUser(),
                s -> new HashSet<>());
        followedUsers.add(userFollowedUserEvent.getFollowingUser());
    }

    public Set<String> getUserFollowingUsers(String user) {
        return userFollowingUsers.getOrDefault(user, Collections.emptySet());
    }

}
