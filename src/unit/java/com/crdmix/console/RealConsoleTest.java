package com.crdmix.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.console.ConsoleListener;
import com.crdmix.console.RealConsole;
import com.crdmix.unit.config.AbstractUnitBase;

public class RealConsoleTest extends AbstractUnitBase<RealConsole> {
    InputStream InputStream = new ByteArrayInputStream("My Message\n".getBytes(StandardCharsets.UTF_8));
    @Mock
    PrintStream printStream;
    private String message = "My message";
    @Mock
    private ConsoleListener crdMixConsoleListener;

    @After
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
        underTest.writeLineToStdOut(message);
        verify(printStream).println(message);
    }

    @Test
    public void lineReadFromInputNotifiesListener() throws InterruptedException {
        underTest.setInputListener(crdMixConsoleListener);
        underTest.start();
        verify(crdMixConsoleListener, timeout(1000)).handleConsoleInput("My Message");
    }

}
