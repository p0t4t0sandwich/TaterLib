package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.Event;

import java.util.ArrayList;
import java.util.function.Consumer;

/** The EventHolder class. */
public class EventManager<T extends Event> {
    private final Class<T> eventClass;
    private final ArrayList<Consumer<T>> listeners = new ArrayList<>();

    public EventManager(Class<T> eventClass, Consumer<T> listener) {
        this.eventClass = eventClass;
        this.listeners.add(listener);
    }

    public EventManager(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<T> eventClass() {
        return this.eventClass;
    }

    public void register(Consumer<T> listener) {
        this.listeners.add(listener);
    }

    public ArrayList<Consumer<T>> listeners() {
        return this.listeners;
    }

    public void invoke(T event) {
        for (Consumer<T> listener : listeners) {
            listener.accept(event);
        }
    }
}
