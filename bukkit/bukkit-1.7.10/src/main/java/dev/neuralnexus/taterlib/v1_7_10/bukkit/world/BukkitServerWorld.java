package dev.neuralnexus.taterlib.v1_7_10.bukkit.world;

import dev.neuralnexus.taterlib.world.ServerWorld;
import dev.neuralnexus.taterlib.world.World;

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
