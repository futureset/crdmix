package com.crdmix.domain.console;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.command.FollowUserCommand;
import com.crdmix.command.PostMessageCommand;
import com.crdmix.domain.eventsource.CommandFactory;
import com.crdmix.domain.eventsource.CommandInvoker;
import com.crdmix.read.ReadPostsService;
import com.crdmix.unit.config.AbstractUnitBase;

public class ConsoleCommandDispatcherTest extends AbstractUnitBase<ConsoleCommandDispatcher> {
    @Mock
    CommandFactory commandFactory;
    @Mock
    CommandInvoker commandInvoker;
    @Mock
    ReadPostsService readPostsService;

    @DontInject
    private String username = "Ben";
    @DontInject
    private String message = "My Test post!";
    @DontInject
    private String otherUser = "Alice";
    @Mock
    private PostMessageCommand postMessageCommand;
    @Mock
    private FollowUserCommand followUsercommand;

    @After
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
        verifyZeroInteractions(readPostsService, commandInvoker, commandFactory);
    }

}
