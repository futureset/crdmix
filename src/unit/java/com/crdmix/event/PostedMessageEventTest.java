package com.crdmix.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.Mock;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.listener.CrdEventListener;
import com.crdmix.unit.config.AbstractUnitBase;
import com.crdmix.unit.config.UnitUtil;

public class PostedMessageEventTest extends AbstractUnitBase<PostedMessageEvent> {

    private DateTime dateTime = new DateTime(0L);
    private String user = "ben";
    private String message = "hello";
    @Mock
    private CrdEventListener listener;

    @Test
    public void checkMessagePopulatedCorrectlyFromParameters() {
        assertThat(underTest.getMessage()).isEqualTo(message);
        assertThat(underTest.getUser()).isEqualTo(user);
        assertThat(underTest.getEventTime()).isEqualTo(dateTime);
    }

    @Test
    public void checkImmutable() {
        assertThat(UnitUtil.mutableFields(clazz)).isEmpty();
    }

    @Test
    public void acceptMethodMakesTheWriteCallBackToTheEventListener() {
        underTest.accept(listener);
        verify(listener).handleMessagePosted(underTest);
    }
}
