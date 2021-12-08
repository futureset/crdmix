package com.crdmix.bdd.configuration;

import com.crdmix.config.AppConfiguration;
import com.crdmix.console.render.ProgrammableClock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration extends AppConfiguration {

    @Bean
    @Override
    public FakeConsole console() {
        return new FakeConsole();
    }

    @Bean
    @Override
    public ProgrammableClock clock() {
        return ProgrammableClock.programmableClock();
    }
}
