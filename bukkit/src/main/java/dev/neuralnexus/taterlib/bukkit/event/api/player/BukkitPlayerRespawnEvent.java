package dev.neuralnexus.taterlib.bukkit.event.api.player;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.utils.Location;

/**
 * An implementation of {@link PlayerRespawnEvent} for Bukkit.
 */
public class BukkitPlayerRespawnEvent extends BukkitPlayerEvent implements PlayerRespawnEvent {
    private final org.bukkit.event.player.PlayerRespawnEvent event;

    public BukkitPlayerRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer() {
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
        return event.isAnchorSpawn();
    }
}
