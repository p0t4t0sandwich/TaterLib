/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.event.network.RegisterPacketChannelsEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Plugin message events. */
public class NetworkEvents {
    /** Called when a plugin message is received. */
    public static final EventManager<C2SCustomPacketEvent> C2S_CUSTOM_PACKET =
            new EventManager<>(C2SCustomPacketEvent.class);

    /** Called when a plugin message is received from a player. */
    public static final EventManager<C2SCustomPacketEvent.Player> PLAYER_PLUGIN_MESSAGE =
            new EventManager<>(C2SCustomPacketEvent.Player.class);

    /** Called when a plugin message is received from a server. */
    public static final EventManager<C2SCustomPacketEvent.Server> SERVER_PLUGIN_MESSAGE =
            new EventManager<>(C2SCustomPacketEvent.Server.class);

    /** Called when plugin messages channels are registered. */
    public static final EventManager<RegisterPacketChannelsEvent> REGISTER_PLUGIN_MESSAGES =
            new EventManager<>(RegisterPacketChannelsEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(
                Arrays.asList(
                        C2S_CUSTOM_PACKET,
                        PLAYER_PLUGIN_MESSAGE,
                        SERVER_PLUGIN_MESSAGE,
                        REGISTER_PLUGIN_MESSAGES));
    }
}
