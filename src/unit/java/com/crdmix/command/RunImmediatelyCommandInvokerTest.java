package com.crdmix.command;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.domain.eventsource.Command;
import com.crdmix.unit.config.AbstractUnitBase;

public class RunImmediatelyCommandInvokerTest extends AbstractUnitBase<RunImmediatelyCommandInvoker> {

    @Mock
    private Command command;

    @Test
    public void testThatInvocationOfInvokerJustExecutesTheCommandImmediately() {
        underTest.invokeCommand(command);
        verify(command).execute();
    }
}
