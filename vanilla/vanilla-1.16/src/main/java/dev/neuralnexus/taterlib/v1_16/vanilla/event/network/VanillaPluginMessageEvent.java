/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.vanilla.event.network;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterlib.v1_16.vanilla.entity.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PluginMessageEvent}. */
public class VanillaPluginMessageEvent implements PluginMessageEvent {
    private final CustomPayload payload;

    public VanillaPluginMessageEvent(CustomPayload packet) {
        this.payload = packet;
    }

    @Override
    public CustomPayload packet() {
        return this.payload;
    }

    /** Vanilla implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends VanillaPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final ServerPlayer player;

        public Player(CustomPayload packet, ServerPlayer player) {
            super(packet);
            this.player = player;
        }

        @Override
        public dev.neuralnexus.taterapi.entity.player.Player player() {
            return new VanillaPlayer(this.player);
        }
    }
}
