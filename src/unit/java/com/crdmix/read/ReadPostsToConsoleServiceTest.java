package com.crdmix.read;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.crdmix.console.SimpleConsole;
import com.crdmix.console.render.PostMessageRenderer;
import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.listener.aggregate.UserFollowingAggregate;
import com.crdmix.event.listener.aggregate.UserTimelineAggregate;
import com.crdmix.unit.config.AbstractUnitBase;

public class ReadPostsToConsoleServiceTest extends AbstractUnitBase<ReadPostsToConsoleService> {
    @Mock
    SimpleConsole simpleConsole;
    @Mock
    UserTimelineAggregate userTimeLineAggregate;
    @Mock
    PostMessageRenderer postMessageRenderer;
    @Mock
    UserFollowingAggregate userFollowingAggregate;
    private String user = "ben";

    private List<PostedMessageEvent> events = new ArrayList<PostedMessageEvent>();
    private List<PostedMessageEvent> otherUserevents = new ArrayList<PostedMessageEvent>();
    @Mock
    private PostedMessageEvent userPostedMessageEvent;
    @Mock
    private PostedMessageEvent otherUserPostedMessageEvent;
    private String renderedUserPostedEvent = "hello";
    private String renderedOtherUserPostedEvent = "bye";
    private String otherUser = "Alice";

    @Before
    public void setEventTimes() {
        DateTime dateTime = new DateTime();
        when(userPostedMessageEvent.getEventTime()).thenReturn(dateTime);
        when(otherUserPostedMessageEvent.getEventTime()).thenReturn(new DateTime(dateTime.getMillis() + 1000));
    }

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
        events.add(userPostedMessageEvent);
        otherUserevents.add(otherUserPostedMessageEvent);
        given(userFollowingAggregate.getUserFollowingUsers(user)).willReturn(new HashSet<>(Arrays.asList(otherUser)));
        given(userTimeLineAggregate.getTimeLineForUser(user)).willReturn(events.stream());
        given(userTimeLineAggregate.getTimeLineForUser(otherUser)).willReturn(otherUserevents.stream());
        given(postMessageRenderer.renderUserEvent(userPostedMessageEvent)).willReturn(renderedUserPostedEvent);
        given(postMessageRenderer.renderUserEvent(otherUserPostedMessageEvent))
                .willReturn(renderedOtherUserPostedEvent);
        underTest.readUserWall(user);
        InOrder inOrder = Mockito.inOrder(simpleConsole);
        inOrder.verify(simpleConsole).writeLineToStdOut(renderedUserPostedEvent);
        inOrder.verify(simpleConsole).writeLineToStdOut(renderedOtherUserPostedEvent);
    }
}
