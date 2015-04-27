package com.crdmix.bdd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.crdmix.config.AppConfiguration;

@Configuration
public class TestConfiguration extends AppConfiguration {

    @Bean
    @Override
    public FakeConsole console() {
        FakeConsole fakeConsole = new FakeConsole();
        return fakeConsole;
    }

}
