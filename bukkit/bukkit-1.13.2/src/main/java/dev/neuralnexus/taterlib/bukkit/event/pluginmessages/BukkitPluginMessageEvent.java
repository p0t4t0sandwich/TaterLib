package dev.neuralnexus.taterlib.bukkit.event.pluginmessages;

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
    public String getChannel() {
        return this.channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] getData() {
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
        public dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer getPlayer() {
            return new dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer(this.player);
        }
    }
}
