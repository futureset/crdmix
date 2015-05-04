package com.crdmix.command;


public class RunImmediatelyCommandInvoker implements CommandInvoker {

    @Override
    public void invokeCommand(Command command) {
        command.execute();
    }

}
