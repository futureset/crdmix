package com.crdmix.read;

import com.crdmix.console.SimpleConsole;
import com.crdmix.console.render.PostMessageRenderer;
import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.listener.aggregate.UserFollowingAggregate;
import com.crdmix.event.listener.aggregate.UserTimelineAggregate;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReadPostsToConsoleServiceTest extends AbstractUnitBase<ReadPostsToConsoleService> {
    @Mock
    SimpleConsole simpleConsole;
    @Mock
    UserTimelineAggregate userTimeLineAggregate;
    @Mock
    PostMessageRenderer postMessageRenderer;
    @Mock
    UserFollowingAggregate userFollowingAggregate;
    private final String user = "ben";

    private final List<PostedMessageEvent> events = new ArrayList<>();
    private final List<PostedMessageEvent> otherUserevents = new ArrayList<>();
    @Mock
    private PostedMessageEvent userPostedMessageEvent;
    @Mock
    private PostedMessageEvent otherUserPostedMessageEvent;
    private final String renderedUserPostedEvent = "hello";
    private final String otherUser = "Alice";

    @Test
    public void readAUsersTimeLineRenderingResultsToConsole() {
        events.add(userPostedMessageEvent);
        given(userTimeLineAggregate.getTimeLineForUser(user)).willReturn(events.stream());
        given(postMessageRenderer.renderEvent(userPostedMessageEvent)).willReturn(renderedUserPostedEvent);
        underTest.readUserTimeLine(user);
        verify(simpleConsole).writeLineToStdOut(renderedUserPostedEvent);
    }

    @Test
    public void canReadAUsersTotheWallAggregatingFollowingUserPosts() {
        Instant dateTime = Instant.now();
        when(userPostedMessageEvent.getEventTime()).thenReturn(dateTime);
        when(otherUserPostedMessageEvent.getEventTime()).thenReturn(dateTime.plus(1000, ChronoUnit.MILLIS));
        events.add(userPostedMessageEvent);
        otherUserevents.add(otherUserPostedMessageEvent);
        given(userFollowingAggregate.getUserFollowingUsers(user)).willReturn(new HashSet<>(Collections.singletonList(otherUser)));
        given(userTimeLineAggregate.getTimeLineForUser(user)).willReturn(events.stream());
        given(userTimeLineAggregate.getTimeLineForUser(otherUser)).willReturn(otherUserevents.stream());
        given(postMessageRenderer.renderUserEvent(userPostedMessageEvent)).willReturn(renderedUserPostedEvent);
        String renderedOtherUserPostedEvent = "bye";
        given(postMessageRenderer.renderUserEvent(otherUserPostedMessageEvent))
                .willReturn(renderedOtherUserPostedEvent);
        underTest.readUserWall(user);
        InOrder inOrder = Mockito.inOrder(simpleConsole);
        inOrder.verify(simpleConsole).writeLineToStdOut(renderedUserPostedEvent);
        inOrder.verify(simpleConsole).writeLineToStdOut(renderedOtherUserPostedEvent);
    }
}
