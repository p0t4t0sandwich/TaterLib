/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.EventManager;
import dev.neuralnexus.taterapi.event.loader.LoaderInitializeEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** Loader events. */
public class LoaderEvents {
    /** Called when the loader is initialized. */
    public static final EventManager<LoaderInitializeEvent> INIT =
            new EventManager<>(LoaderInitializeEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(Collections.singletonList(INIT));
    }
}
