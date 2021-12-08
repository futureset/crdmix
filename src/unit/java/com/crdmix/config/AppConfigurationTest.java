package com.crdmix.config;

import com.crdmix.console.ConsoleListener;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppConfigurationTest extends AbstractUnitBase<AppConfiguration> {

    @Test
    public void hasAConsoleListenerDefined() {
        assertThat(underTest.consoleListener()).isInstanceOf(ConsoleListener.class);
    }
}
