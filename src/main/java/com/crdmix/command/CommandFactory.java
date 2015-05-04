package com.crdmix.command;


public interface CommandFactory {

    PostMessageCommand createPostUserMessage(String username, String commandParameter);

    FollowUserCommand createUserFollowsUser(String username, String commandParameter);

}
