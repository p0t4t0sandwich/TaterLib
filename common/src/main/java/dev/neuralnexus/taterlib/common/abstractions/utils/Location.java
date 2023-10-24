package dev.neuralnexus.taterlib.common.abstractions.utils;

public class Location extends Position {
    private final float yaw;
    private final float pitch;
    // TODO: Abstract world
    private final String world;

    /**
     * Creates a new location.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     * @param yaw The yaw.
     * @param pitch The pitch.
     * @param world The world.
     */
    public Location(double x, double y, double z, float yaw, float pitch, String world) {
        super(x, y, z);
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /**
     * Getter for the yaw.
     * @return The yaw.
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Getter for the pitch.
     * @return The pitch.
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Getter for the world.
     * @return The world.
     */
    public String getWorld() {
        return world;
    }
}
