/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.api.EventManager;
import dev.neuralnexus.taterloader.event.plugin.PluginDisableEvent;
import dev.neuralnexus.taterloader.event.plugin.PluginEnableEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Plugin message events. */
public class PluginEvents {
    /** Called when a plugins are enabled. */
    public static final EventManager<PluginEnableEvent> ENABLED =
            new EventManager<>(PluginEnableEvent.class);

    /** Called when a plugins are disabled. */
    public static final EventManager<PluginDisableEvent> DISABLED =
            new EventManager<>(PluginDisableEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(Arrays.asList(ENABLED, DISABLED));
    }
}
