/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network.impl;

import dev.neuralnexus.taterapi.event.network.C2SCustomPacketEvent;
import dev.neuralnexus.taterapi.event.network.CustomPacketEvent;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;

/** General implementation of {@link C2SCustomPacketEvent}. */
public class CustomPacketEventImpl implements CustomPacketEvent {
    private final CustomPayloadPacket packet;

    public CustomPacketEventImpl(CustomPayloadPacket packet) {
        this.packet = packet;
    }

    @Override
    public CustomPayloadPacket packet() {
        return this.packet;
    }
}
