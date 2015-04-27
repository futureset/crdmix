package com.crdmix.command;

import com.crdmix.domain.eventsource.Command;
import com.crdmix.domain.eventsource.EventFactory;
import com.crdmix.domain.eventsource.EventStore;

abstract public class AbstractEventGeneratingCommand extends AbstractUserCommand implements Command {

    protected EventStore eventStore;
    protected EventFactory eventFactory;

    public AbstractEventGeneratingCommand(String user, EventStore eventStore, EventFactory eventFactory) {
        super(user);
        this.eventStore = eventStore;
        this.eventFactory = eventFactory;
    }

}
