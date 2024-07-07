/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.bukkit.world;

import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterapi.world.World;

/** Bukkit implementation of {@link World}. */
public class BukkitServerWorld extends BukkitWorld implements ServerWorld {
    private final org.bukkit.World world;

    /**
     * Creates a new world.
     *
     * @param world The Bukkit world.
     */
    public BukkitServerWorld(org.bukkit.World world) {
        super(world);
        this.world = world;
    }

    /**
     * Gets the Bukkit world.
     *
     * @return The Bukkit world.
     */
    public org.bukkit.World world() {
        return world;
    }
}
