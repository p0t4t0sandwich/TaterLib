package dev.neuralnexus.taterlib.bukkit.event.api.player;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Bukkit implementation of {@link PlayerEvent}.
 */
public class BukkitPlayerEvent implements PlayerEvent {
    private final org.bukkit.event.player.PlayerEvent event;

    BukkitPlayerEvent(org.bukkit.event.player.PlayerEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new BukkitPlayer(event.getPlayer());
    }
}
