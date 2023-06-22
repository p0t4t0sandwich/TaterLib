package dev.neuralnexus.taterapi.common.events.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class TaterEvent<T> {
    private final Class<T> eventClass;

    public TaterEvent(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public void addListener(Runnable listener) {
        EventBus.INSTANCE.addListener(eventClass, listener);
    }
}
