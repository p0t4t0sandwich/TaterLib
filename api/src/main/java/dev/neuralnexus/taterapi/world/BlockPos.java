/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.world;

/** Represents a position in the world */
public record BlockPos(double x, double y, double z) {
    /**
     * Creates a new position.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    public BlockPos {}

    /**
     * Getter for the block x position.
     *
     * @return The block x position.
     */
    public int blockX() {
        return (int) Math.floor(this.x);
    }

    /**
     * Getter for the block y position.
     *
     * @return The block y position.
     */
    public int blockY() {
        return (int) Math.floor(this.y);
    }

    /**
     * Getter for the block z position.
     *
     * @return The block z position.
     */
    public int blockZ() {
        return (int) Math.floor(this.z);
    }
}
