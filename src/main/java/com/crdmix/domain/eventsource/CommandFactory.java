package com.crdmix.domain.eventsource;

import com.crdmix.command.FollowUserCommand;
import com.crdmix.command.PostMessageCommand;

public interface CommandFactory {

    PostMessageCommand createPostUserMessage(String username, String commandParameter);

    FollowUserCommand createUserFollowsUser(String username, String commandParameter);

}
