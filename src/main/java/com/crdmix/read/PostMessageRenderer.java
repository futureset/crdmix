package com.crdmix.read;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTimeUtils;

import com.crdmix.domain.event.PostedMessageEvent;

public class PostMessageRenderer {

    public String getMessage(boolean includeUser, PostedMessageEvent event) {
        StringBuilder result = new StringBuilder(1000);
        if (includeUser) {
            result.append(event.getUser()).append(" - ");
        }
        result.append(event.getMessage()).append(" (");
        long messageAge = DateTimeUtils.currentTimeMillis() - event.getEventTime().getMillis();
        if (messageAge < 1000) {
            result.append("Just now)");
        } else {
            final long amount;
            if (messageAge < 60 * 1000) {
                amount = TimeUnit.SECONDS.convert(messageAge, TimeUnit.MILLISECONDS);
                result.append(amount).append(" second");
            } else {
                amount = TimeUnit.MINUTES.convert(messageAge, TimeUnit.MILLISECONDS);
                result.append(amount).append(" minute");
            }
            result.append(amount > 1 ? "s " : " ").append("ago)");
        }
        return result.toString();

    }
}
