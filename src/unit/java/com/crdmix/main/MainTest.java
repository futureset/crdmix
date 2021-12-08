package com.crdmix.main;

import com.crdmix.config.AppConfiguration;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class MainTest extends AbstractUnitBase<Main> {

    @Mock
    private AnnotationConfigApplicationContext mockCtx;

    @Test
    public void checkDefaultContextIsSet() {
        assertThat(Main.getCtx()).isExactlyInstanceOf(AnnotationConfigApplicationContext.class);
    }

    @Test
    public void useAFakeContextToTestStarting() throws InterruptedException {
        Main.setCtx(mockCtx);
        Main.main();

        verify(mockCtx).register(AppConfiguration.class);
        verify(mockCtx).refresh();

    }
}
