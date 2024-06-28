/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.sponge.adapters;

import net.minecraft.world.entity.player.Player;

/** Adapts Sponge classes to NMS classes. */
public class SpongeAdapters {
    /**
     * Returns a Vanilla player from a Sponge player.
     *
     * @param player The Sponge player.
     * @return The Vanilla player.
     */
    public static Player player(org.spongepowered.api.entity.living.player.Player player) {
        return (Player) player;
    }
}
