package com.crdmix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.crdmix.command.CrdCommandFactory;
import com.crdmix.command.RunImmediatelyCommandInvoker;
import com.crdmix.domain.console.ConsoleListener;
import com.crdmix.domain.console.ConsoleCommandDispatcher;
import com.crdmix.domain.console.RealConsole;
import com.crdmix.domain.console.SimpleConsole;
import com.crdmix.domain.event.CrdEventFactory;
import com.crdmix.domain.event.NotifyingEventStore;
import com.crdmix.domain.eventsource.CommandFactory;
import com.crdmix.domain.eventsource.CommandInvoker;
import com.crdmix.domain.eventsource.EventFactory;
import com.crdmix.read.PostMessageRenderer;
import com.crdmix.read.ReadPostsService;
import com.crdmix.read.ReadPostsToConsoleService;
import com.crdmix.read.aggregate.UserFollowingAggregate;
import com.crdmix.read.aggregate.UserTimelineAggregate;

@Configuration
public class AppConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public SimpleConsole console() {
        RealConsole realConsole = new RealConsole();
        return realConsole;
    }

    @Bean
    public ConsoleListener consoleListener() {
        ConsoleCommandDispatcher crdMixConsoleListener = new ConsoleCommandDispatcher(commandFactory(), commandInvoker(),
                readMessagesService());
        console().setInputListener(crdMixConsoleListener);
        return crdMixConsoleListener;
    }

    @Bean
    public ReadPostsService readMessagesService() {
        return new ReadPostsToConsoleService(console(), userTimelineAggregate(), postMessageRenderer(),
                userFollowingAggregate());
    }

    @Bean
    public PostMessageRenderer postMessageRenderer() {
        return new PostMessageRenderer();
    }

    @Bean
    public CommandInvoker commandInvoker() {
        return new RunImmediatelyCommandInvoker();
    }

    @Bean
    public CommandFactory commandFactory() {
        return new CrdCommandFactory(eventStore(), eventFactory());
    }

    @Bean
    public EventFactory eventFactory() {
        return new CrdEventFactory();
    }

    @Bean
    public NotifyingEventStore eventStore() {
        return new NotifyingEventStore();
    }

    @Bean
    public UserFollowingAggregate userFollowingAggregate() {
        UserFollowingAggregate userFollowingAggregate = new UserFollowingAggregate();
        eventStore().registerListener(userFollowingAggregate);
        return userFollowingAggregate;
    }

    @Bean
    public UserTimelineAggregate userTimelineAggregate() {
        UserTimelineAggregate userTimelineAggregate = new UserTimelineAggregate();
        eventStore().registerListener(userTimelineAggregate);
        return userTimelineAggregate;

    }
}
