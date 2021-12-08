package com.crdmix.config;

import com.crdmix.command.CommandFactory;
import com.crdmix.command.CommandInvoker;
import com.crdmix.command.CrdCommandFactory;
import com.crdmix.command.RunImmediatelyCommandInvoker;
import com.crdmix.console.ConsoleCommandDispatcher;
import com.crdmix.console.ConsoleListener;
import com.crdmix.console.RealConsole;
import com.crdmix.console.SimpleConsole;
import com.crdmix.console.render.PostMessageRenderer;
import com.crdmix.event.CrdEventFactory;
import com.crdmix.event.EventFactory;
import com.crdmix.event.NotifyingEventStore;
import com.crdmix.event.listener.aggregate.UserFollowingAggregate;
import com.crdmix.event.listener.aggregate.UserTimelineAggregate;
import com.crdmix.read.ReadPostsService;
import com.crdmix.read.ReadPostsToConsoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class AppConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public SimpleConsole console() {
        return new RealConsole();
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
        return new PostMessageRenderer(clock());
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
        return new CrdEventFactory(clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
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
