package com.crdmix.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.domain.event.PostedMessageEvent;
import com.crdmix.unit.config.AbstractUnitBase;

public class PostMessageRendererTest extends AbstractUnitBase<PostMessageRenderer> {

    @Mock
    PostedMessageEvent event;
    private DateTime eventTime = new DateTime();
    private String user = "ben";
    private String message = "hello";

    @Before
    public void freezeTime() {
        DateTimeUtils.setCurrentMillisFixed(eventTime.getMillis());
        given(event.getEventTime()).willReturn(eventTime);
        given(event.getUser()).willReturn(user);
        given(event.getMessage()).willReturn(message);
    }

    @Test
    public void checkPostMessageCanBeRenderedWithUserPrefix() {
        assertThat(underTest.getMessage(true, event)).isEqualTo(user + " - " + message + " (Just now)");
    }

    @Test
    public void checkPostMessageCanBeRenderedWithoutUserPrefix() {
        assertThat(underTest.getMessage(false, event)).isEqualTo(message + " (Just now)");
    }

    @Test
    public void checkAnyMessageLessThanASecondOldReportsAsjustNow() {
        DateTimeUtils.setCurrentMillisFixed(eventTime.getMillis() + 999);
        assertThat(underTest.getMessage(false, event)).isEqualTo(message + " (Just now)");
    }

    @Test
    public void checkAnyMessageMoreThanASecondButLessThanAMinuteOldReportsNumberOfSeconds() {
        DateTimeUtils.setCurrentMillisFixed(eventTime.getMillis() + 1000);
        assertThat(underTest.getMessage(false, event)).isEqualTo(message + " (1 second ago)");
        DateTimeUtils.setCurrentMillisFixed(eventTime.getMillis() + 5000);
        assertThat(underTest.getMessage(false, event)).isEqualTo(message + " (5 seconds ago)");
    }

    @Test
    public void checkAnyMessageOlderThanAMinuteOrMoreReportsNumberOfMinutes() {
        DateTimeUtils.setCurrentMillisFixed(eventTime.getMillis() + 60000);
        assertThat(underTest.getMessage(false, event)).isEqualTo(message + " (1 minute ago)");
        DateTimeUtils.setCurrentMillisFixed(eventTime.getMillis() + 120000);
        assertThat(underTest.getMessage(false, event)).isEqualTo(message + " (2 minutes ago)");
    }
}
