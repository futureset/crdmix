package com.crdmix.bdd.configuration;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.crdmix.bdd.steps.WriteCommandSteps;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackageClasses = WriteCommandSteps.class)
public class JBehaveConfig {

    private static final List<Class<?>> stepTypes = new ArrayList<>();

    @Bean
    public Embedder embedder() {
        Embedder embedder = new Embedder();
        embedder.useConfiguration(configuration());
        embedder.useStepsFactory(stepsFactory());
        return embedder;
    }

    @Bean
    public InjectableStepsFactory stepsFactory() {
        InjectableStepsFactory stepsFactory = new SpringStepsFactory(configuration(), stepTypes);
        return stepsFactory;
    }

    @Bean
    public Configuration configuration() {
        Configuration configuration = new MostUsefulConfiguration();
        configuration.useStoryLoader(new LoadFromClasspath()).useStoryReporterBuilder(
                new StoryReporterBuilder().withCodeLocation(CodeLocations.codeLocationFromClass(JBehaveConfig.class))
                        .withDefaultFormats().withFormats(CONSOLE, TXT, HTML, XML));
        return configuration;
    }

    @Bean
    public JBehaveRule jBehaveRule() {
        return new JBehaveRule(embedder(), "stories/");
    }

    @Bean
    public static BeanPostProcessor scanForStepTypesInContainer() {
        return new BeanPostProcessor() {
            private final Set<Class<?>> jbehaveAnnotations;
            {
                jbehaveAnnotations = new HashSet<>();
                jbehaveAnnotations.addAll(Arrays.asList(Given.class, When.class, Then.class, Before.class, After.class,
                        BeforeScenario.class, AfterScenario.class, BeforeStory.class, AfterStory.class,
                        BeforeStories.class, AfterStories.class));
            }

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                for (Method m : bean.getClass().getMethods()) {
                    for (Annotation ann : m.getAnnotations()) {
                        if (jbehaveAnnotations.contains(ann.annotationType())) {
                            stepTypes.add(bean.getClass());
                            return bean;
                        }
                    }
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

        };
    }
}
