/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.world.entity.player.Player;

/** Vanilla implementation of {@link PlayerRespawnEvent}. */
public class VanillaPlayerRespawnEvent extends VanillaPlayerEvent implements PlayerRespawnEvent {
    private final boolean alive;
    private final Player player;

    public VanillaPlayerRespawnEvent(Player player, boolean alive) {
        super(player);
        this.player = player;
        this.alive = alive;
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new VanillaLocation(player);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBedSpawn() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
