package com.crdmix.command;

import com.crdmix.domain.eventsource.Command;
import com.crdmix.domain.eventsource.CommandInvoker;

public class RunImmediatelyCommandInvoker implements CommandInvoker {

    @Override
    public void invokeCommand(Command command) {
        command.execute();
    }

}
