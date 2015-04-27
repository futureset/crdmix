package com.crdmix.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.crdmix.domain.console.ConsoleListener;
import com.crdmix.unit.config.AbstractUnitBase;

public class AppConfigurationTest extends AbstractUnitBase<AppConfiguration> {

    @Test
    public void hasAConsoleListenerDefined() {
        assertThat(underTest.consoleListener()).isInstanceOf(ConsoleListener.class);
    }
}
