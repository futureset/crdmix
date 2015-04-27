package com.crdmix.domain.console;

public interface SimpleConsole {

    void incomingLineToStdIn(String line);

    void writeLineToStdOut(String line);

    void start();

    void stop();

    void setInputListener(ConsoleListener crdMixConsoleListener);
}
