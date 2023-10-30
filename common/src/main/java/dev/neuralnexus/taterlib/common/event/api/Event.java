package dev.neuralnexus.taterlib.common.event.api;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * These are really sketchy at the moment, don't use them.
 */
public class Event<T> {
    private final Class<T> eventClass;
    private final ArrayList<Consumer<T>> listeners = new ArrayList<>();

    public Event(Class<T> eventClass, Consumer<T> listener) {
        this.eventClass = eventClass;
        this.listeners.add(listener);
    }

    public Event(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<T> getEventClass() {
        return this.eventClass;
    }

    public void register(Consumer<T> listener) {
        this.listeners.add(listener);
    }

    public ArrayList<Consumer<T>> getListeners() {
        return this.listeners;
    }

    public void invoke(T event) {
        for (Consumer<T> listener : listeners) {
            listener.accept(event);
        }
    }
}
