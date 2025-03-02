/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.event.player;

import cpw.mods.fml.common.gameevent.PlayerEvent;

import dev.neuralnexus.taterapi.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.forge.entity.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_7_10.forge.world.ForgeLocation;

/** Forge implementation of {@link PlayerRespawnEvent}. */
public class ForgePlayerRespawnEvent extends ForgePlayerEvent implements PlayerRespawnEvent {

    public ForgePlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        super(event);
    }

    @Override
    public Location respawnLocation() {
        return new ForgeLocation(((ForgePlayer) player()).unwrap());
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
