/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;

/** General implementation of {@link C2SCustomPacketEvent}. */
public class C2SCustomPacketEventImpl extends CustomPacketEventImpl
        implements C2SCustomPacketEvent {
    private final User player;

    public C2SCustomPacketEventImpl(CustomPayloadPacket packet, User player) {
        super(packet);
        this.player = player;
    }

    @Override
    public User player() {
        return this.player;
    }
}
