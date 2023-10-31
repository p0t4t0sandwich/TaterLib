package dev.neuralnexus.taterlib.sponge.util;

import com.flowpowered.math.vector.Vector3d;
import dev.neuralnexus.taterlib.common.utils.Position;

/**
 * Conversion utilities for Sponge
 */
public final class SpongeConversions {
    /**
     * Converts a Vector to a Position
     * @param location The Location
     * @return The Position
     */
    public static Position positionFromVector(Vector3d location) {
        return new Position(location.getX(), location.getY(), location.getZ());
    }
}
