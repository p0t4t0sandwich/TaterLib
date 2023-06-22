package dev.neuralnexus.taterapi.common.events.api;

import dev.neuralnexus.taterapi.common.listeners.player.PlayerLoginListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EventBus {
    public static final EventBus INSTANCE = new EventBus();
    private final Map<Class<?>, ArrayList<Runnable>> map = new HashMap<>();

    public <T> void addListener(Class<T> eventClass, Runnable listener) {
        ArrayList<Runnable> listeners = map.computeIfAbsent(eventClass, k -> new ArrayList<>());
        listeners.add(listener);
    }

    public <T> void invoke(Class<T> eventClass) {
        ArrayList<Runnable> listeners = map.get(eventClass);
        if (listeners == null) {
            throw new NoSuchElementException("No listeners for event " + eventClass);
        }
        for (Runnable listener : listeners) {
            listener.run();
        }
    }
}