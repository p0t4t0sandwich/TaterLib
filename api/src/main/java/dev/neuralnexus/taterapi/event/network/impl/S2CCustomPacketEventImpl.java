/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.S2CCustomPacketEvent;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;
import dev.neuralnexus.taterapi.server.SimpleServer;

import org.jspecify.annotations.NonNull;

/** General implementation of {@link S2CCustomPacketEvent}. */
public class S2CCustomPacketEventImpl extends CustomPacketEventImpl
        implements S2CCustomPacketEvent {
    private final SimpleServer server;

    public S2CCustomPacketEventImpl(
            final @NonNull CustomPacketPayload payload, final @NonNull SimpleServer server) {
        super(payload);
        this.server = server;
    }

    @Override
    public @NonNull SimpleServer server() {
        return this.server;
    }
}
