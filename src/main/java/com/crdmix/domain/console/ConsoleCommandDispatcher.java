package com.crdmix.domain.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crdmix.command.FollowUserCommand;
import com.crdmix.command.PostMessageCommand;
import com.crdmix.domain.eventsource.CommandFactory;
import com.crdmix.domain.eventsource.CommandInvoker;
import com.crdmix.read.ReadPostsService;

public class ConsoleCommandDispatcher implements ConsoleListener {

    private CommandFactory commandFactory;
    private CommandInvoker commandInvoker;

    final Pattern commandPattern = Pattern.compile("\\S+");
    private ReadPostsService readMessagesService;

    public ConsoleCommandDispatcher(CommandFactory commandFactory, CommandInvoker commandInvoker,
            ReadPostsService readMessagesService) {
        super();
        this.commandFactory = commandFactory;
        this.commandInvoker = commandInvoker;
        this.readMessagesService = readMessagesService;

    }

    @Override
    public void handleConsoleInput(String rawCommand) {
        Matcher matcher = commandPattern.matcher(rawCommand);
        if (matcher.find()) {
            final String username = matcher.group();
            if (matcher.find()) {
                final String commandType = matcher.group();
                if (matcher.find()) {
                    final String commandParameter = matcher.group();
                    if (commandType.equals(PostMessageCommand.POST)) {
                        commandInvoker.invokeCommand(commandFactory.createPostUserMessage(username,
                                rawCommand.substring(matcher.start())));
                    } else if (commandType.equals(FollowUserCommand.FOLLOWS)) {
                        commandInvoker.invokeCommand(commandFactory.createUserFollowsUser(username, commandParameter));
                    }
                } else if (commandType.equals(ReadPostsService.WALL)) {
                    readMessagesService.readUserWall(username);
                }
            } else {
                readMessagesService.readUserTimeLine(username);
            }
        }
    }

}
