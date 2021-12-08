package com.crdmix.event.listener.aggregate;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UserTimelineAggregateTest extends AbstractUnitBase<UserTimelineAggregate> {

    private final String user = "Ben";
    @Mock
    private PostedMessageEvent postedMessageEvent;
    private final String message = "Hello everyone!";

    @Test
    public void getUsertimeLineIsEmpty() {
        assertThat(underTest.getTimeLineForUser(user).findFirst()).isEmpty();
    }

    @Test
    public void afterAUserAsPostedThenTimeLineHasAnEvent() {
        given(postedMessageEvent.getUser()).willReturn(user);
        underTest.handleMessagePosted(postedMessageEvent);
        assertThat(underTest.getTimeLineForUser(user).findFirst()).contains(postedMessageEvent);
    }
}
