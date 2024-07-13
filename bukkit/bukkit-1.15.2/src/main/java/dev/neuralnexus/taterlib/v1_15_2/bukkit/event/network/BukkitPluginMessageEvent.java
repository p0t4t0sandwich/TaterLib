/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15_2.bukkit.event.network;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.player.BukkitPlayer;

/** Bukkit implementation of {@link PluginMessageEvent}. */
public class BukkitPluginMessageEvent implements PluginMessageEvent {
    private final CustomPayload packet;

    public BukkitPluginMessageEvent(CustomPayload packet) {
        this.packet = packet;
    }

    @Override
    public CustomPayload packet() {
        return this.packet;
    }

    /** Bukkit implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends BukkitPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final org.bukkit.entity.Player player;

        public Player(CustomPayload packet, org.bukkit.entity.Player player) {
            super(packet);
            this.player = player;
        }

        @Override
        public BukkitPlayer player() {
            return new BukkitPlayer(this.player);
        }
    }
}
