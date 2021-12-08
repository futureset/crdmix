package com.crdmix.console;

import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;

public class ConsoleListenerTest extends AbstractUnitBase<ConsoleListener> {

    @Override
    protected ConsoleListener createInstance() {
        return ConsoleListener.NULL_CONSOLE_LISTENER;
    }

    @Test
    public void checkNullListenerDoesNothing() {
        underTest.handleConsoleInput("nothing");
    }

}
