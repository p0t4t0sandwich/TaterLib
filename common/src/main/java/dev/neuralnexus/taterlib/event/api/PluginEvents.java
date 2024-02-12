package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.event.plugin.PluginDisableEvent;
import dev.neuralnexus.taterlib.event.plugin.PluginEnableEvent;

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
