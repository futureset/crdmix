package com.crdmix.console.render;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTimeUtils;

import com.crdmix.event.PostedMessageEvent;

public class PostMessageRenderer {

    public String renderEvent(PostedMessageEvent event) {
        StringBuilder result = new StringBuilder(1000);
        return render(event, result).toString();

    }

    private StringBuilder render(PostedMessageEvent event, StringBuilder result) {
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
        return result;
    }

    public String renderUserEvent(PostedMessageEvent postedMessage) {
        StringBuilder result = new StringBuilder(1000);
        result.append(postedMessage.getUser()).append(" - ");
        return render(postedMessage, result).toString();
    }
}
