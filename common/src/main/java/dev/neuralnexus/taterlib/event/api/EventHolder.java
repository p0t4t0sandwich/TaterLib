package dev.neuralnexus.taterlib.event.api;

import java.util.ArrayList;
import java.util.function.Consumer;

/** The EventHolder class. */
public class EventHolder<T> {
    private final Class<T> eventClass;
    private final ArrayList<Consumer<T>> listeners = new ArrayList<>();

    public EventHolder(Class<T> eventClass, Consumer<T> listener) {
        this.eventClass = eventClass;
        this.listeners.add(listener);
    }

    public EventHolder(Class<T> eventClass) {
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
