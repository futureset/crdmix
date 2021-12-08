package com.crdmix.command;

import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class RunImmediatelyCommandInvokerTest extends AbstractUnitBase<RunImmediatelyCommandInvoker> {

    @Mock
    private Command command;

    @Test
    public void testThatInvocationOfInvokerJustExecutesTheCommandImmediately() {
        underTest.invokeCommand(command);
        verify(command).execute();
    }
}
