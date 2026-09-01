/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.event.network.CustomPacketEvent;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;

import org.jspecify.annotations.NonNull;

/** General implementation of {@link C2SCustomPacketEvent}. */
public class CustomPacketEventImpl implements CustomPacketEvent {
    private final CustomPacketPayload payload;

    public CustomPacketEventImpl(final @NonNull CustomPacketPayload payload) {
        this.payload = payload;
    }

    @Override
    public @NonNull CustomPacketPayload payload() {
        return this.payload;
    }
}
