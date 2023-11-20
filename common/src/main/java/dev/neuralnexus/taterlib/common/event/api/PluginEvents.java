package dev.neuralnexus.taterlib.common.event.api;

import dev.neuralnexus.taterlib.common.event.plugin.PluginDisableEvent;
import dev.neuralnexus.taterlib.common.event.plugin.PluginEnableEvent;

/**
 * Plugin message events.
 */
public class PluginEvents {
    /**
     * Called when a plugins are enabled.
     */
    public static final Event<PluginEnableEvent> ENABLED = new Event<>(PluginEnableEvent.class);

    /**
     * Called when a plugins are disabled.
     */
    public static final Event<PluginDisableEvent> DISABLED = new Event<>(PluginDisableEvent.class);
}
