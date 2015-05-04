package com.crdmix.command;

import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;

public class CrdCommandFactory implements CommandFactory {

    private EventStore eventStore;
    private EventFactory eventFactory;

    public CrdCommandFactory(EventStore eventStore, EventFactory eventFactory) {
        super();
        this.eventStore = eventStore;
        this.eventFactory = eventFactory;
    }

    @Override
    public PostMessageCommand createPostUserMessage(String username, String message) {
        return new PostMessageCommand(username, message, eventStore, eventFactory);
    }

    @Override
    public FollowUserCommand createUserFollowsUser(String username, String userToFollow) {
        return new FollowUserCommand(username, userToFollow, eventFactory, eventStore);
    }

}
