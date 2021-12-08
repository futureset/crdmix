package com.crdmix.console.render;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.unit.config.AbstractUnitBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class PostMessageRendererTest extends AbstractUnitBase<PostMessageRenderer> {

    @Mock
    PostedMessageEvent event;
    private final ProgrammableClock programmableClock = ProgrammableClock.programmableClock();
    private final Instant now = programmableClock.instant();
    private final Instant eventTime = now;
    private final String message = "hello";

    @Override
    protected PostMessageRenderer createInstance() {
        return new PostMessageRenderer(programmableClock);
    }

    @BeforeEach
    public void freezeTime() {
        given(event.getEventTime()).willReturn(eventTime);
        given(event.getMessage()).willReturn(message);
    }

    @Test
    public void checkPostMessageCanBeRenderedWithUserPrefix() {
        String user = "ben";
        given(event.getUser()).willReturn(user);
        assertThat(underTest.renderUserEvent(event)).isEqualTo(user + " - " + message + " (Just now)");
    }

    @Test
    public void checkPostMessageCanBeRenderedWithoutUserPrefix() {
        assertThat(underTest.renderEvent(event)).isEqualTo(message + " (Just now)");
    }

    @Test
    public void checkAnyMessageLessThanASecondOldReportsAsjustNow() {
        assertThat(underTest.renderEvent(event)).isEqualTo(message + " (Just now)");
    }

    @Test
    public void checkAnyMessageMoreThanASecondButLessThanAMinuteOldReportsNumberOfSeconds() {
        programmableClock.setMillisecondsPast(1000);
        assertThat(underTest.renderEvent(event)).isEqualTo(message + " (1 second ago)");
        programmableClock.setMillisecondsPast(5000);
        assertThat(underTest.renderEvent(event)).isEqualTo(message + " (5 seconds ago)");
    }

    @Test
    public void checkAnyMessageOlderThanAMinuteOrMoreReportsNumberOfMinutes() {
        programmableClock.setMillisecondsPast(60000);
        assertThat(underTest.renderEvent(event)).isEqualTo(message + " (1 minute ago)");
        programmableClock.setMillisecondsPast(120000);
        assertThat(underTest.renderEvent(event)).isEqualTo(message + " (2 minutes ago)");
    }
}
