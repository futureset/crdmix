package com.crdmix.event;

import com.crdmix.console.render.ProgrammableClock;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrdEventFactoryTest extends AbstractUnitBase<CrdEventFactory> {

    private final String user = "ben";
    private ProgrammableClock clock;

    @Override
    protected CrdEventFactory createInstance() {
        clock = ProgrammableClock.programmableClock();
        return new CrdEventFactory(clock);
    }

    @Test
    public void canCreateAPostEvent() {
        String message = "my message";
        PostedMessageEvent userPostedMessage = underTest.userPostedMessage(user, message);
        assertThat(userPostedMessage.getUser()).isEqualTo(user);
        assertThat(userPostedMessage.getMessage()).isEqualTo(message);
        assertThat(userPostedMessage.getEventTime()).isEqualTo(clock.instant());
    }

    @Test
    public void canCreateAUserFollowsEvent() {
        String followingUser = "alice";
        UserFollowedUserEvent userFollowEvent = underTest.userFollowedUser(user, followingUser);
        assertThat(userFollowEvent.getUser()).isEqualTo(user);
        assertThat(userFollowEvent.getFollowingUser()).isEqualTo(followingUser);
        assertThat(userFollowEvent.getEventTime()).isEqualTo(clock.instant());
    }
}
