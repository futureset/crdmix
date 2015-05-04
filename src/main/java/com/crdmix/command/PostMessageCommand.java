package com.crdmix.command;

import com.crdmix.event.CrdEvent;
import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;

public class PostMessageCommand extends AbstractEventGeneratingCommand {

    private final String message;
    public static final String POST = "->";

    public PostMessageCommand(String username, String message, EventStore eventStore, EventFactory eventFactory) {
        super(username, eventStore, eventFactory);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        CrdEvent event = eventFactory.userPostedMessage(user, message);
        eventStore.storeEvent(event);
    }

}
