package dev.neuralnexus.taterlib.forge.abstrations.util;

import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

/**
 * Conversion utilities for Forge
 */
public final class ForgeConversions {
    /**
     * Converts a Vector to a Position
     * @param location The Location
     * @return The Position
     */
    public static Position positionFromVector(Vec3 location) {
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
