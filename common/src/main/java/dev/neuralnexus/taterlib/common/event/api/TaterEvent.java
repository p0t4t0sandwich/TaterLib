package dev.neuralnexus.taterlib.common.event.api;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * These are really sketchy at the moment, don't use them.
 */
@Deprecated
public class TaterEvent<T> {
    private final Class<T> eventClass;
    private final ArrayList<Consumer> listeners = new ArrayList<>();

    public TaterEvent(Class<T> eventClass, Consumer<Object[]> listener) {
        this.eventClass = eventClass;
        this.listeners.add(listener);
    }

    public Class<T> getEventClass() {
        return this.eventClass;
    }

    public void addListener(Consumer listener) {
        this.listeners.add(listener);
    }

    public ArrayList<Consumer> getListeners() {
        return this.listeners;
    }

    public void call(T event) {
        for (Consumer listener : listeners) {
            listener.accept(event);
        }
    }
}
