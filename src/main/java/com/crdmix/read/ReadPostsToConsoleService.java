package com.crdmix.read;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.crdmix.console.SimpleConsole;
import com.crdmix.console.render.PostMessageRenderer;
import com.crdmix.event.listener.aggregate.UserFollowingAggregate;
import com.crdmix.event.listener.aggregate.UserTimelineAggregate;

public class ReadPostsToConsoleService implements ReadPostsService {
    private SimpleConsole console;
    private UserTimelineAggregate userTimeLines;
    private PostMessageRenderer postMessageRenderer;
    private UserFollowingAggregate userFollowingAggregate;

    public ReadPostsToConsoleService(SimpleConsole console, UserTimelineAggregate userTimeLines,
            PostMessageRenderer postMessageRenderer, UserFollowingAggregate userFollowingAggregate) {
        super();
        this.console = console;
        this.userTimeLines = userTimeLines;
        this.postMessageRenderer = postMessageRenderer;
        this.userFollowingAggregate = userFollowingAggregate;
    }

    @Override
    public void readUserTimeLine(String user) {
        userTimeLines.getTimeLineForUser(user).forEach(
                postedMessage -> console.writeLineToStdOut(postMessageRenderer.renderEvent(postedMessage)));

    }

    @Override
    public void readUserWall(String user) {
        Set<String> users = new HashSet<>(userFollowingAggregate.getUserFollowingUsers(user));
        users.stream()
                .map(u -> userTimeLines.getTimeLineForUser(u))
                .reduce(userTimeLines.getTimeLineForUser(user), (a, b) -> Stream.concat(a, b))
                .sorted(Comparator.comparing(e -> e.getEventTime()))
                .forEach(postedMessage -> console.writeLineToStdOut(postMessageRenderer.renderUserEvent(postedMessage)));

    }
}
