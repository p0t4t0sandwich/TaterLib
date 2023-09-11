package dev.neuralnexus.taterlib.sponge.abstractions.util;

import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import org.spongepowered.math.vector.Vector3d;

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
        return new Position(location.x(), location.y(), location.z());
    }
}
