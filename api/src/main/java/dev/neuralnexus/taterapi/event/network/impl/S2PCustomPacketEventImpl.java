/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.S2PCustomPacketEvent;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;
import dev.neuralnexus.taterapi.server.SimpleServer;

import org.jspecify.annotations.NonNull;

/** General implementation of {@link S2PCustomPacketEvent}. */
public class S2PCustomPacketEventImpl extends CustomPacketEventImpl
        implements S2PCustomPacketEvent {
    private final SimpleServer server;

    public S2PCustomPacketEventImpl(
            final @NonNull CustomPacketPayload payload, final @NonNull SimpleServer server) {
        super(payload);
        this.server = server;
    }

    @Override
    public @NonNull SimpleServer server() {
        return this.server;
    }
}
