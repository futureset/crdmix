package com.crdmix.console;

public interface ConsoleListener {

    default void handleConsoleInput(String rawCommand) {
    };

    public static final ConsoleListener NULL_CONSOLE_LISTENER = new ConsoleListener() {
    };

}
