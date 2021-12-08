package com.crdmix.console;

import com.crdmix.command.CommandFactory;
import com.crdmix.command.CommandInvoker;
import com.crdmix.command.FollowUserCommand;
import com.crdmix.command.PostMessageCommand;
import com.crdmix.read.ReadPostsService;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ConsoleCommandDispatcherTest extends AbstractUnitBase<ConsoleCommandDispatcher> {
    @Mock
    CommandFactory commandFactory;
    @Mock
    CommandInvoker commandInvoker;
    @Mock
    ReadPostsService readPostsService;

    @DontInject
    private final String username = "Ben";
    @DontInject
    private final String otherUser = "Alice";
    @Mock
    private PostMessageCommand postMessageCommand;
    @Mock
    private FollowUserCommand followUsercommand;

    @AfterEach
    public void noUnexpectedActions() {
        verifyNoMoreInteractions(readPostsService, commandInvoker);
    }

    @Test
    public void readUserTimeLineQueriesServiceDirectly() {
        underTest.handleConsoleInput(username);
        verify(readPostsService).readUserTimeLine(username);
    }

    @Test
    public void readUserWallCommandQueriesServiceDirectly() {
        underTest.handleConsoleInput(username + " wall");
        verify(readPostsService).readUserWall(username);
    }

    @Test
    public void postMessageIssuesCommand() {
        String message = "My Test post!";
        given(commandFactory.createPostUserMessage(username, message)).willReturn(postMessageCommand);
        underTest.handleConsoleInput(username + " -> " + message);
        verify(commandInvoker).invokeCommand(postMessageCommand);

    }

    @Test
    public void followUserIssuesCommand() {
        given(commandFactory.createUserFollowsUser(username, otherUser)).willReturn(followUsercommand);
        underTest.handleConsoleInput(username + " follows " + otherUser);
        verify(commandInvoker).invokeCommand(followUsercommand);
    }

    @Test
    public void unrecognizedCommandsDoNothing() {
        underTest.handleConsoleInput(username + " invalidCommand " + otherUser);
        underTest.handleConsoleInput(username + " invalidCommand");
        underTest.handleConsoleInput("");
        verifyNoInteractions(readPostsService, commandInvoker, commandFactory);
    }

}
