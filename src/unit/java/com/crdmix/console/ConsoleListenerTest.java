package com.crdmix.console;

import org.junit.Test;

import com.crdmix.console.ConsoleListener;
import com.crdmix.unit.config.AbstractUnitBase;

public class ConsoleListenerTest extends AbstractUnitBase<ConsoleListener> {

    private String rawCommand = "nothing";

    @Override
    protected ConsoleListener createInstance() throws Exception {
        return ConsoleListener.NULL_CONSOLE_LISTENER;
    }

    @Test
    public void checkNullListenerDoesNothing() {
        underTest.handleConsoleInput(rawCommand);
    }

}
