package dev.neuralnexus.taterlib.bukkit.event.pluginmessages;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.pluginmessages.AbstractPluginMessageEvent;
import org.bukkit.entity.Player;

/**
 * Bukkit implementation of {@link AbstractPluginMessageEvent}.
 */
public class BukkitPluginMessageEvent implements AbstractPluginMessageEvent {
    private final String channel;
    private final byte[] data;

    public BukkitPluginMessageEvent(String channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getChannel() {
        return this.channel;
    }

    /**
     * @inheritDoc
     */
    @Override
    public byte[] getData() {
        return this.data;
    }

    /**
     * Bukkit implementation of {@link AbstractPlayerPluginMessageEvent}.
     */
    public static class BukkitPlayerPluginMessageEvent extends BukkitPluginMessageEvent implements AbstractPlayerPluginMessageEvent {
        private final Player player;
        public BukkitPlayerPluginMessageEvent(String channel, byte[] data, Player player) {
            super(channel, data);
            this.player = player;
        }

        /**
         * @inheritDoc
         */
        @Override
        public BukkitPlayer getPlayer() {
            return new BukkitPlayer(this.player);
        }
    }
}
