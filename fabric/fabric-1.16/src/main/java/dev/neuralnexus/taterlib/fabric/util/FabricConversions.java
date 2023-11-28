package dev.neuralnexus.taterlib.fabric.util;

import dev.neuralnexus.taterlib.utils.Position;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/** Conversion utilities for Fabric */
public final class FabricConversions {
    /**
     * Converts a Vector to a Position
     *
     * @param location The Location
     * @return The Position
     */
    public static Position positionFromVector(Vec3d location) {
        return new Position(location.getX(), location.getY(), location.getZ());
    }

    /**
     * Converts a Position to a Block Location
     *
     * @param position The Position
     * @return The Fabric Location
     */
    public static BlockPos locationFromPosition(Position position) {
        return new BlockPos((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }
}
