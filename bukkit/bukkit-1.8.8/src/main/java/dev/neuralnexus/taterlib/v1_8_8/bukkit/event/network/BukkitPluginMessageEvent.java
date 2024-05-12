package dev.neuralnexus.taterlib.v1_8_8.bukkit.event.network;

import dev.neuralnexus.taterlib.v1_8_8.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.network.PluginMessageEvent;

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
