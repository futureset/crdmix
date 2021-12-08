package com.crdmix.console.render;

import com.crdmix.event.PostedMessageEvent;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PostMessageRenderer {

    private final Clock clock;

    public PostMessageRenderer(Clock clock) {
        this.clock = clock;
    }

    public String renderEvent(PostedMessageEvent event) {
        StringBuilder result = new StringBuilder(1000);
        return render(event, result).toString();

    }

    private StringBuilder render(PostedMessageEvent event, StringBuilder result) {
        result.append(event.getMessage()).append(" (");
        long messageAge = Duration.between(event.getEventTime(), clock.instant()).toMillis();
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
