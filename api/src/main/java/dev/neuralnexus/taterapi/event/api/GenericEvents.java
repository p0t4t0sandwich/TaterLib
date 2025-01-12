/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;

import java.util.HashSet;
import java.util.Set;

/** Generic event system. */
public class GenericEvents {
    /** Called when the event is fired. */
    public static final EventManager<Event> EVENT = new EventManager<>(Event.class);

    /** Set of all event managers. */
    public static final Set<EventManager<? extends Event>> EVENTS = new HashSet<>();

    static {
        EVENTS.addAll(BlockEvents.events());
        EVENTS.addAll(CommandEvents.events());
        EVENTS.addAll(EntityEvents.events());
        EVENTS.addAll(LoaderEvents.events());
        EVENTS.addAll(NetworkEvents.events());
        EVENTS.addAll(PlayerEvents.events());
        EVENTS.addAll(PluginEvents.events());
        EVENTS.addAll(ServerEvents.events());
    }

    /** Set up generic events. */
    public static void setup() {
        EVENTS.forEach(event -> event.register(EVENT::invoke));
    }
}
