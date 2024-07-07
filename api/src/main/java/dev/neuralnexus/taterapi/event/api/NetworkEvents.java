/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterapi.event.network.RegisterPluginMessagesEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Plugin message events. */
public class NetworkEvents {
    /** Called when a plugin message is received. */
    public static final EventManager<PluginMessageEvent> PLUGIN_MESSAGE =
            new EventManager<>(PluginMessageEvent.class);

    /** Called when a plugin message is received from a player. */
    public static final EventManager<PluginMessageEvent.Player> PLAYER_PLUGIN_MESSAGE =
            new EventManager<>(PluginMessageEvent.Player.class);

    /** Called when a plugin message is received from a server. */
    public static final EventManager<PluginMessageEvent.Server> SERVER_PLUGIN_MESSAGE =
            new EventManager<>(PluginMessageEvent.Server.class);

    /** Called when plugin messages channels are registered. */
    public static final EventManager<RegisterPluginMessagesEvent> REGISTER_PLUGIN_MESSAGES =
            new EventManager<>(RegisterPluginMessagesEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(
                Arrays.asList(
                        PLUGIN_MESSAGE,
                        PLAYER_PLUGIN_MESSAGE,
                        SERVER_PLUGIN_MESSAGE,
                        REGISTER_PLUGIN_MESSAGES));
    }
}
