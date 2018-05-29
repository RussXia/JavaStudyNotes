package com.xzy.guava.eventbus.simpledemo;

import com.google.common.eventbus.Subscribe;

public class EventListener {

    private String eventMeeage;

    @Subscribe
    public void listen(EventMessage eventMessage) {
        this.eventMeeage = eventMessage.getMessage();
        System.out.println("receive message:" + eventMeeage);
    }

    public String getEventMeeage() {
        return eventMeeage;
    }
}
