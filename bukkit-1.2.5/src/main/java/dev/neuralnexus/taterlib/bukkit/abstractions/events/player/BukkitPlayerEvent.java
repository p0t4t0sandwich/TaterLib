package dev.neuralnexus.taterlib.bukkit.abstractions.events.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import org.bukkit.event.player.PlayerEvent;

/**
 * Bukkit implementation of {@link AbstractPlayerEvent}.
 */
public class BukkitPlayerEvent implements AbstractPlayerEvent {
    private final PlayerEvent event;

    BukkitPlayerEvent(PlayerEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new BukkitPlayer(event.getPlayer());
    }
}
