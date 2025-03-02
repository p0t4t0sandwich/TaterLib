/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11_2.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_11_2.fabric.entity.player.FabricPlayer;
import dev.neuralnexus.taterlib.v1_11_2.fabric.world.FabricLocation;

import net.minecraft.entity.player.PlayerEntity;

/** Fabric implementation of {@link PlayerRespawnEvent}. */
public class FabricPlayerRespawnEvent extends FabricPlayerEvent implements PlayerRespawnEvent {

    public FabricPlayerRespawnEvent(PlayerEntity player, int dimension, boolean alive) {
        super(player);
    }

    @Override
    public Location respawnLocation() {
        return new FabricLocation(((FabricPlayer) this.player()).unwrap());
    }

    @Override
    public boolean isBedSpawn() {
        return false;
    }

    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
