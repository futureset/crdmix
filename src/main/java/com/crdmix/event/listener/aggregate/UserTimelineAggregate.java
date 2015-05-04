package com.crdmix.event.listener.aggregate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.crdmix.event.PostedMessageEvent;
import com.crdmix.event.listener.CrdEventListener;

public class UserTimelineAggregate implements CrdEventListener {

    private final Map<String, List<PostedMessageEvent>> usertimelines = new HashMap<String, List<PostedMessageEvent>>();

    public UserTimelineAggregate() {
        super();
    }

    @Override
    public void handleMessagePosted(PostedMessageEvent postedMessageEvent) {
        List<PostedMessageEvent> messages = usertimelines.computeIfAbsent(postedMessageEvent.getUser(),
                f -> new ArrayList<>());
        messages.add(postedMessageEvent);
    }

    public Stream<PostedMessageEvent> getTimeLineForUser(String username) {
        return usertimelines.getOrDefault(username, Collections.emptyList()).stream();
    }

}
