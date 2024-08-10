/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.S2CCustomPacketEvent;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.server.SimpleServer;

/** General implementation of {@link S2CCustomPacketEvent}. */
public class S2CCustomPacketEventImpl extends CustomPacketEventImpl
        implements S2CCustomPacketEvent {
    private final SimpleServer server;

    public S2CCustomPacketEventImpl(CustomPayloadPacket packet, SimpleServer server) {
        super(packet);
        this.server = server;
    }

    @Override
    public SimpleServer server() {
        return this.server;
    }
}
