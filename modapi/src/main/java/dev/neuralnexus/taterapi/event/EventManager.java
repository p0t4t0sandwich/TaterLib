/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event;

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
