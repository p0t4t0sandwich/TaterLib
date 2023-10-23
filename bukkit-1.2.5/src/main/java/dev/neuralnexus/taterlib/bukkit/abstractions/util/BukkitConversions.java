package dev.neuralnexus.taterlib.bukkit.abstractions.util;

import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import org.bukkit.Location;

/**
 * Misc utilities for Bukkit
 */
public final class BukkitConversions {
    /**
     * Converts a Location to a Position
     * @param location The Location
     * @return The Position
     */
    public static Position positionFromLocation(Location location) {
        return new Position(location.getX(), location.getY(), location.getZ());
    }

    /**
     * Converts a Position to a Bukkit Location
     * @param position The Position
     * @return The Bukkit Location
     */
    public static Location locationFromPosition(Position position) {
        return new Location(null, position.getX(), position.getY(), position.getZ());
    }
}
