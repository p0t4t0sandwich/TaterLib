package dev.neuralnexus.taterlib.common.event.api;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * These are really sketchy at the moment, don't use them.
 */
public class Event<T, R> {
    private final Class<T> eventClass;
    private final ArrayList<Consumer<R>> listeners = new ArrayList<>();

    public Event(Class<T> eventClass, Consumer<R> listener) {
        this.eventClass = eventClass;
        this.listeners.add(listener);
    }

    public Event(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<T> getEventClass() {
        return this.eventClass;
    }

    public void register(Consumer<R> listener) {
        this.listeners.add(listener);
    }

    public ArrayList<Consumer<R>> getListeners() {
        return this.listeners;
    }

    public void invoke(R event) {
        for (Consumer<R> listener : listeners) {
            listener.accept(event);
        }
    }
}
