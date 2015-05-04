package com.crdmix.command;

import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;

abstract public class AbstractEventGeneratingCommand extends AbstractUserCommand implements Command {

    protected EventStore eventStore;
    protected EventFactory eventFactory;

    public AbstractEventGeneratingCommand(String user, EventStore eventStore, EventFactory eventFactory) {
        super(user);
        this.eventStore = eventStore;
        this.eventFactory = eventFactory;
    }

}
