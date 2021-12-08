package com.crdmix.console;

import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class RealConsoleTest extends AbstractUnitBase<RealConsole> {
    InputStream InputStream = new ByteArrayInputStream("My Message\n".getBytes(StandardCharsets.UTF_8));
    @Mock
    PrintStream printStream;
    @Mock
    private ConsoleListener crdMixConsoleListener;

    @AfterEach
    public void shutdownAfter() {
        underTest.stop();
    }

    @Test
    public void defaultConstructorUsesSystemInAndOut() {
        RealConsole realConsole = new RealConsole();
        assertThat(realConsole.getReader()).isEqualTo(System.in);
        assertThat(realConsole.getWriter()).isEqualTo(System.out);
    }

    @Test
    public void canWriteASimpleLineOut() {
        String message = "My message";
        underTest.writeLineToStdOut(message);
        verify(printStream).println(message);
    }

    @Test
    public void lineReadFromInputNotifiesListener() {
        underTest.setInputListener(crdMixConsoleListener);
        underTest.start();
        verify(crdMixConsoleListener, timeout(1000)).handleConsoleInput("My Message");
    }

}
