package com.crdmix.command;

import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;

public class FollowUserCommand extends AbstractEventGeneratingCommand {

    private final String userToFollow;
    public static final String FOLLOWS = "follows";

    public FollowUserCommand(String username, String userToFollow, EventFactory eventFactory, EventStore eventStore) {
        super(username, eventStore, eventFactory);
        this.userToFollow = userToFollow;
    }

    public String getUserToFollow() {
        return userToFollow;
    }

    @Override
    public void execute() {
        eventStore.storeEvent(eventFactory.userFollowedUser(user, userToFollow));
    }

}
