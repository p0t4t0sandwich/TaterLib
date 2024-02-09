package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.plugin.PluginDisableEvent;
import dev.neuralnexus.taterlib.event.plugin.PluginEnableEvent;

/** Plugin message events. */
public class PluginEvents {
    /** Called when a plugins are enabled. */
    public static final EventHolder<PluginEnableEvent> ENABLED =
            new EventHolder<>(PluginEnableEvent.class);

    /** Called when a plugins are disabled. */
    public static final EventHolder<PluginDisableEvent> DISABLED =
            new EventHolder<>(PluginDisableEvent.class);
}
