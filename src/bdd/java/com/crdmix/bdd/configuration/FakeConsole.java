package com.crdmix.bdd.configuration;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.crdmix.domain.console.AbstractConsole;

public class FakeConsole extends AbstractConsole {

    private static final int SCREEN_BUFFER_LINE_SIZE = 1000;
    private final Queue<String> screen;
    private final Queue<String> stdIn = new ConcurrentLinkedQueue<>();

    @Override
    public void incomingLineToStdIn(String line) {
        stdIn.add(line);
        super.incomingLineToStdIn(line);

    }

    public FakeConsole() {
        super();
        this.screen = new LinkedBlockingQueue<String>(SCREEN_BUFFER_LINE_SIZE);
    }

    @Override
    public void writeLineToStdOut(String line) {
        while (!screen.offer(line)) {
            screen.poll();
        }
    }

    public Queue<String> getScreen() {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>(screen);
        screen.clear();
        return concurrentLinkedQueue;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

}
