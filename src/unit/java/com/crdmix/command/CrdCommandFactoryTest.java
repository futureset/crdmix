package com.crdmix.command;

import com.crdmix.event.EventFactory;
import com.crdmix.event.EventStore;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class CrdCommandFactoryTest extends AbstractUnitBase<CrdCommandFactory> {
    @Mock
    EventStore eventStore;
    @Mock
    EventFactory eventFactory;
    private final String username = "Ben";

    @Test
    public void canCreateAFollowUserCommand() {
        String userToFollow = "Alice";
        FollowUserCommand followUserCommand = underTest.createUserFollowsUser(username, userToFollow);
        assertThat(followUserCommand.getUser()).isEqualTo(username);
        assertThat(followUserCommand.getUserToFollow()).isEqualTo(userToFollow);
    }

    @Test
    public void canCreateAUSerPostCommand() {
        String message = "Hello again";
        PostMessageCommand postMessageCommand = underTest.createPostUserMessage(username, message);
        assertThat(postMessageCommand.getUser()).isEqualTo(username);
        assertThat(postMessageCommand.getMessage()).isEqualTo(message);
    }
}
