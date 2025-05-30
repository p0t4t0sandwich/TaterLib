/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.EventManager;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Command events. */
public final class CommandEvents {
    /** Called when the command register event is fired. */
    public static final EventManager<CommandRegisterEvent> REGISTER_COMMAND =
            new EventManager<>(CommandRegisterEvent.class);

    /** Called when the brigadier command register event is fired. */
    @SuppressWarnings("rawtypes")
    public static final EventManager<BrigadierCommandRegisterEvent> REGISTER_BRIGADIER_COMMAND =
            new EventManager<>(BrigadierCommandRegisterEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(Arrays.asList(REGISTER_COMMAND, REGISTER_BRIGADIER_COMMAND));
    }
}
