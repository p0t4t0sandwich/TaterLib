package dev.neuralnexus.taterlib.v1_7_10.bukkit.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.world.BukkitLocation;
import dev.neuralnexus.taterlib.world.Location;

/** An implementation of {@link PlayerRespawnEvent} for Bukkit. */
public class BukkitPlayerRespawnEvent extends BukkitPlayerEvent implements PlayerRespawnEvent {
    private final org.bukkit.event.player.PlayerRespawnEvent event;

    public BukkitPlayerRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new BukkitPlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new BukkitLocation(event.getRespawnLocation());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBedSpawn() {
        return event.isBedSpawn();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
