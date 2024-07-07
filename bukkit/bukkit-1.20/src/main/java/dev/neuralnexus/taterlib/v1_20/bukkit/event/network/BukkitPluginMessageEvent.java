/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.bukkit.event.network;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.player.BukkitPlayer;

/** Bukkit implementation of {@link PluginMessageEvent}. */
public class BukkitPluginMessageEvent implements PluginMessageEvent {
    private final String channel;
    private final byte[] data;

    public BukkitPluginMessageEvent(String channel, byte[] data) {
        this.channel = channel;
        this.data = data;
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

    /** Bukkit implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends BukkitPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final org.bukkit.entity.Player player;

        public Player(String channel, byte[] data, org.bukkit.entity.Player player) {
            super(channel, data);
            this.player = player;
        }

        /** {@inheritDoc} */
        @Override
        public BukkitPlayer player() {
            return new BukkitPlayer(this.player);
        }
    }
}
