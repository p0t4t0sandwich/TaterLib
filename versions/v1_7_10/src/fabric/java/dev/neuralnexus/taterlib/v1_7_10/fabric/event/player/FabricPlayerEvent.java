/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.entity.player.EntityPlayer;

/** Fabric implementation of {@link PlayerEvent}. */
public class FabricPlayerEvent implements PlayerEvent {
    private final EntityPlayer player;

    public FabricPlayerEvent(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public Player player() {
        return new WrappedPlayer(this.player);
    }
}
