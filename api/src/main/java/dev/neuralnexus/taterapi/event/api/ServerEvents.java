/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.EventManager;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Server events. */
public class ServerEvents {
    /** Called when the server is starting. */
    public static final EventManager<ServerStartingEvent> STARTING =
            new EventManager<>(ServerStartingEvent.class);

    /** Called when the server has started. */
    public static final EventManager<ServerStartedEvent> STARTED =
            new EventManager<>(ServerStartedEvent.class);

    /** Called when the server is stopping. */
    public static final EventManager<ServerStoppingEvent> STOPPING =
            new EventManager<>(ServerStoppingEvent.class);

    /** Called when the server has stopped. */
    public static final EventManager<ServerStoppedEvent> STOPPED =
            new EventManager<>(ServerStoppedEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(Arrays.asList(STARTING, STARTED, STOPPING, STOPPED));
    }
}
