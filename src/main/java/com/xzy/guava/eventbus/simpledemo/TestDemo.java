package com.xzy.guava.eventbus.simpledemo;

import com.google.common.eventbus.EventBus;

public class TestDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test_event_bus");
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);
        eventBus.post(new EventMessage("Test Message A"));
        eventBus.post(new EventMessage("Test Message B"));
        eventBus.post(new EventMessage("Test Message C"));
        eventBus.post(new EventMessage("Test Message D"));

        System.out.println(eventListener.getEventMeeage());
    }
}
