/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;

import org.jspecify.annotations.NonNull;

/** General implementation of {@link C2SCustomPacketEvent}. */
public class C2SCustomPacketEventImpl extends CustomPacketEventImpl
        implements C2SCustomPacketEvent {
    private final User player;

    public C2SCustomPacketEventImpl(
            final @NonNull CustomPacketPayload payload, final @NonNull User player) {
        super(payload);
        this.player = player;
    }

    @Override
    public @NonNull User player() {
        return this.player;
    }
}
