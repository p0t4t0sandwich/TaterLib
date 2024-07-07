/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerEvent;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterlib.v1_7_10.fabric.player.FabricPlayer;

import net.minecraft.entity.player.PlayerEntity;

/** Fabric implementation of {@link PlayerEvent}. */
public class FabricPlayerEvent implements PlayerEvent {
    private final PlayerEntity player;

    public FabricPlayerEvent(PlayerEntity player) {
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new FabricPlayer(this.player);
    }
}
