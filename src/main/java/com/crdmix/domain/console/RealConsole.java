package com.crdmix.domain.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RealConsole extends AbstractConsole implements SimpleConsole {

    private final InputStream reader;
    private final PrintStream writer;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public RealConsole() {
        this(System.in, System.out);
    }

    public RealConsole(InputStream reader, PrintStream writer) {
        super();
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void writeLineToStdOut(String line) {
        writer.println(line);

    }

    public InputStream getReader() {
        return reader;
    }

    public PrintStream getWriter() {
        return writer;
    }

    @Override
    public void start() {
        executor.submit(new ReadInputThread());
        executor.shutdown();
    }

    @Override
    public void stop() {
        if (!executor.isTerminated()) {
            executor.shutdownNow();
        }
    }

    public class ReadInputThread implements Runnable {

        @Override
        public void run() {
            writeLineToStdOut("Welcome");
            try (Scanner scanner = new Scanner(reader)) {
                String nextLine = "";
                while (!nextLine.equals("exit") && scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                    incomingLineToStdIn(nextLine);
                }
            }
            writeLineToStdOut("Bye");

        }

    }

}
