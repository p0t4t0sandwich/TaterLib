/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_6_4.forge.world.ForgeLocation;

import net.minecraft.entity.player.EntityPlayer;

/** Forge implementation of {@link PlayerRespawnEvent}. */
public class ForgePlayerRespawnEvent implements PlayerRespawnEvent {
    private final EntityPlayer player;

    public ForgePlayerRespawnEvent(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public Player player() {
        return new ForgePlayer(player);
    }

    @Override
    public Location respawnLocation() {
        return new ForgeLocation(((ForgePlayer) player()).getPlayer());
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
