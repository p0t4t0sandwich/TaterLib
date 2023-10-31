package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.utils.Location;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * An implementation of {@link AbstractPlayerRespawnEvent} for Bukkit.
 */
public class BukkitPlayerRespawnEvent extends BukkitPlayerEvent implements AbstractPlayerRespawnEvent {
    private final PlayerRespawnEvent event;

    public BukkitPlayerRespawnEvent(PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new BukkitPlayer(event.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new BukkitLocation(event.getRespawnLocation());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBedSpawn() {
        return event.isBedSpawn();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
