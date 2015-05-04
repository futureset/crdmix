package com.crdmix.console;


public abstract class AbstractConsole implements SimpleConsole {

    private ConsoleListener consoleListener = ConsoleListener.NULL_CONSOLE_LISTENER;

    @Override
    public void setInputListener(ConsoleListener consoleListener) {
        this.consoleListener = consoleListener;

    }

    @Override
    public void incomingLineToStdIn(String line) {
        this.consoleListener.handleConsoleInput(line);
    }

}
