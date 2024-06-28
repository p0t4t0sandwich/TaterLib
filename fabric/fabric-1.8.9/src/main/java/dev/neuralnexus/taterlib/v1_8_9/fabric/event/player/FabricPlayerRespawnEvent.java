/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_9.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_8_9.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.v1_8_9.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.entity.player.PlayerEntity;

/** Fabric implementation of {@link PlayerRespawnEvent}. */
public class FabricPlayerRespawnEvent extends FabricPlayerEvent implements PlayerRespawnEvent {
    private final boolean alive;
    private final PlayerEntity player;

    public FabricPlayerRespawnEvent(PlayerEntity player, int dimension, boolean alive) {
        super(player);
        this.player = player;
        this.alive = alive;
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new FabricLocation(((FabricPlayer) player()).player());
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
