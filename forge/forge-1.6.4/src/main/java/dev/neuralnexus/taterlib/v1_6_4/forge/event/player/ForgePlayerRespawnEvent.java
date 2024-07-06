/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_6_4.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_6_4.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.entity.player.EntityPlayer;

/** Forge implementation of {@link PlayerRespawnEvent}. */
public class ForgePlayerRespawnEvent implements PlayerRespawnEvent {
    private final EntityPlayer player;

    public ForgePlayerRespawnEvent(EntityPlayer player) {
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new ForgePlayer(player);
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new ForgeLocation(((ForgePlayer) player()).getPlayer());
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
