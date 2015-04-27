package com.crdmix.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

import org.joda.time.DateTimeUtils;
import org.junit.Test;

import com.crdmix.unit.config.AbstractUnitBase;

public class CrdEventFactoryTest extends AbstractUnitBase<CrdEventFactory> {

    private String message = "my message";
    private String user = "ben";
    private String followingUser = "alice";

    @Test
    public void canCreateAPostEvent() {
        long currentTimeMillis = DateTimeUtils.currentTimeMillis();
        PostedMessageEvent userPostedMessage = underTest.userPostedMessage(user, message);
        assertThat(userPostedMessage.getUser()).isEqualTo(user);
        assertThat(userPostedMessage.getMessage()).isEqualTo(message);
        assertThat(userPostedMessage.getEventTime().getMillis()).isCloseTo(currentTimeMillis, offset(20L));
    }

    @Test
    public void canCreateAUserFollowsEvent() {
        long currentTimeMillis = DateTimeUtils.currentTimeMillis();
        UserFollowedUserEvent userFollowEvent = underTest.userFollowedUser(user, followingUser);
        assertThat(userFollowEvent.getUser()).isEqualTo(user);
        assertThat(userFollowEvent.getFollowingUser()).isEqualTo(followingUser);
        assertThat(userFollowEvent.getEventTime().getMillis()).isCloseTo(currentTimeMillis, offset(20L));
    }
}
