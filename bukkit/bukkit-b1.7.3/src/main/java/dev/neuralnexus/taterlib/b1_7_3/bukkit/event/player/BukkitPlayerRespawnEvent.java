/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.b1_7_3.bukkit.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.world.BukkitLocation;

/** An implementation of {@link PlayerRespawnEvent} for Bukkit. */
public class BukkitPlayerRespawnEvent extends BukkitPlayerEvent implements PlayerRespawnEvent {
    private final org.bukkit.event.player.PlayerRespawnEvent event;

    public BukkitPlayerRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public Player player() {
        return new BukkitPlayer(event.getPlayer());
    }

    @Override
    public Location respawnLocation() {
        return new BukkitLocation(event.getRespawnLocation());
    }

    @Override
    public boolean isBedSpawn() {
        return event.isBedSpawn();
    }

    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
