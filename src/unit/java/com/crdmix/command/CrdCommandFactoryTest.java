package com.crdmix.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;
import com.crdmix.unit.config.AbstractUnitBase;

public class CrdCommandFactoryTest extends AbstractUnitBase<CrdCommandFactory> {
    @Mock
    EventStore eventStore;
    @Mock
    EventFactory eventFactory;
    private String username = "Ben";
    private String userToFollow = "Alice";
    private String message = "Hello again";

    @Test
    public void canCreateAFollowUserCommand() {
        FollowUserCommand followUserCommand = underTest.createUserFollowsUser(username, userToFollow);
        assertThat(followUserCommand.getUser()).isEqualTo(username);
        assertThat(followUserCommand.getUserToFollow()).isEqualTo(userToFollow);
    }

    @Test
    public void canCreateAUSerPostCommand() {
        PostMessageCommand postMessageCommand = underTest.createPostUserMessage(username, message);
        assertThat(postMessageCommand.getUser()).isEqualTo(username);
        assertThat(postMessageCommand.getMessage()).isEqualTo(message);
    }
}
