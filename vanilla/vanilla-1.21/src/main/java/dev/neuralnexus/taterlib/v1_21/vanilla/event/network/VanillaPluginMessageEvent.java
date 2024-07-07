/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.event.network;

import dev.neuralnexus.taterapi.event.network.CustomPayloadWrapper;
import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PluginMessageEvent}. */
public class VanillaPluginMessageEvent implements PluginMessageEvent {
    private final String channel;
    private final byte[] data;

    public VanillaPluginMessageEvent(String channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    public VanillaPluginMessageEvent(CustomPayloadWrapper packet) {
        this.channel = packet.channel();
        this.data = packet.data();
    }

    /** {@inheritDoc} */
    @Override
    public String channel() {
        return this.channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] data() {
        return this.data;
    }

    /** Vanilla implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends VanillaPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final ServerPlayer player;

        public Player(String channel, byte[] data, ServerPlayer player) {
            super(channel, data);
            this.player = player;
        }

        public Player(CustomPayloadWrapper packet, ServerPlayer player) {
            super(packet);
            this.player = player;
        }

        /** {@inheritDoc} */
        @Override
        public dev.neuralnexus.taterapi.player.Player player() {
            return new VanillaPlayer(this.player);
        }
    }
}
