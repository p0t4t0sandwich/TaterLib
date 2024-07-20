/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.world;

/** Represents a position in the world */
public class BlockPos {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Creates a new position.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    public BlockPos(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Getter for the x coordinate.
     *
     * @return The x coordinate.
     */
    public double x() {
        return x;
    }

    /**
     * Getter for the y coordinate.
     *
     * @return The y coordinate.
     */
    public double y() {
        return y;
    }

    /**
     * Getter for the z coordinate.
     *
     * @return The z coordinate.
     */
    public double z() {
        return z;
    }
}
