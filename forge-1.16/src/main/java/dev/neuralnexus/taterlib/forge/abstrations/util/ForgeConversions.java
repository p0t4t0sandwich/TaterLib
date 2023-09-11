package dev.neuralnexus.taterlib.forge.abstrations.util;

import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

/**
 * Conversion utilities for Forge
 */
public final class ForgeConversions {
    /**
     * Converts a Vector to a Position
     * @param location The Location
     * @return The Position
     */
    public static Position positionFromVector(Vector3d location) {
        return new Position(location.x(), location.y(), location.z());
    }

    /**
     * Converts a Position to a Block Location
     * @param position The Position
     * @return The Fabric Location
     */
    public static BlockPos locationFromPosition(Position position) {
        return new BlockPos((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }
}
