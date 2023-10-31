package dev.neuralnexus.taterlib.bukkit.util;

import org.bukkit.Location;

public class BukkitLocation extends dev.neuralnexus.taterlib.common.utils.Location {
    private final Location location;

    /**
     * Creates a new location.
     */
    public BukkitLocation(Location location) {
        super(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), location.getWorld() == null ? null : location.getWorld().getName());
        this.location = location;
    }

    /**
     * Getter for the bukkit location.
     * @return The bukkit location.
     */
    public Location getLocation() {
        return location;
    }
}
