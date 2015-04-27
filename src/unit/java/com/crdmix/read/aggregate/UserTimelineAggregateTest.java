package com.crdmix.read.aggregate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.domain.event.PostedMessageEvent;
import com.crdmix.unit.config.AbstractUnitBase;

public class UserTimelineAggregateTest extends AbstractUnitBase<UserTimelineAggregate> {

    private String user = "Ben";
    @Mock
    private PostedMessageEvent postedMessageEvent;
    private String message = "Hello everyone!";

    @Test
    public void getUsertimeLineIsEmpty() {
        assertThat(underTest.getTimeLineForUser(user).findFirst()).isEmpty();
    }

    @Test
    public void afterAUserAsPostedThenTimeLineHasAnEvent() {
        given(postedMessageEvent.getMessage()).willReturn(message);
        given(postedMessageEvent.getUser()).willReturn(user);
        underTest.handleMessagePosted(postedMessageEvent);
        assertThat(underTest.getTimeLineForUser(user).findFirst()).contains(postedMessageEvent);
    }
}
