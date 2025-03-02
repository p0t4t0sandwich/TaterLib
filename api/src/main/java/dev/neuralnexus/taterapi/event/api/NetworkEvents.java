/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.event.network.RegisterPacketChannelsEvent;
import dev.neuralnexus.taterapi.event.network.S2CCustomPacketEvent;
import dev.neuralnexus.taterapi.event.network.S2PCustomPacketEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Plugin message events. */
public class NetworkEvents {
    /** Called when a custom packet is received from the client to the server. */
    public static final EventManager<C2SCustomPacketEvent> C2S_CUSTOM_PACKET =
            new EventManager<>(C2SCustomPacketEvent.class);

    /** Called when a custom packet is received from the server to the client. */
    public static final EventManager<S2CCustomPacketEvent> S2C_CUSTOM_PACKET =
            new EventManager<>(S2CCustomPacketEvent.class);

    /** Called when a custom packet is received from the server to the proxy. */
    public static final EventManager<S2PCustomPacketEvent> S2P_CUSTOM_PACKET =
            new EventManager<>(S2PCustomPacketEvent.class);

    /** Called when packet channels are registered. */
    public static final EventManager<RegisterPacketChannelsEvent> REGISTER_CHANNELS =
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
                        S2C_CUSTOM_PACKET,
                        S2P_CUSTOM_PACKET,
                        REGISTER_CHANNELS));
    }
}
