package com.crdmix.command;

import com.crdmix.domain.eventsource.Command;

public abstract class AbstractUserCommand implements Command {

    protected final String user;

    public AbstractUserCommand(String user) {
        super();
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

}
