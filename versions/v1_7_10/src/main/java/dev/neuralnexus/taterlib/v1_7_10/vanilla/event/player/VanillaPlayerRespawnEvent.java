/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.VanillaLocation;

import net.minecraft.entity.living.player.PlayerEntity;

/** Vanilla implementation of {@link PlayerRespawnEvent}. */
public class VanillaPlayerRespawnEvent extends VanillaPlayerEvent implements PlayerRespawnEvent {
    private final PlayerEntity player;

    public VanillaPlayerRespawnEvent(PlayerEntity player, int dimension, boolean alive) {
        super(player);
        this.player = player;
    }

    @Override
    public Location respawnLocation() {
        return new VanillaLocation(this.player);
    }

    // TODO: Implement more hooks
    @Override
    public boolean isBedSpawn() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean isAnchorSpawn() {
        throw new VersionFeatureNotSupportedException();
    }
}
