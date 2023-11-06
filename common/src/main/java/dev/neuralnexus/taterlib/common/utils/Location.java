package dev.neuralnexus.taterlib.common.utils;

/**
 * Abstracts a location.
 */
public interface Location {
    // TODO: Abstract world

    /**
     * Setter for the x position.
     * @param x The x position.
     */
    void setX(double x);

    /**
     * Getter for the x position.
     * @return The x position.
     */
    double getX();

    /**
     * Getter for the block x position.
     * @return The block x position.
     */
    double getBlockX();

    /**
     * Setter for the y position.
     * @param y The y position.
     */
    void setY(double y);

    /**
     * Getter for the y position.
     * @return The y position.
     */
    double getY();

    /**
     * Getter for the block y position.
     * @return The block y position.
     */
    double getBlockY();

    /**
     * Setter for the z position.
     * @param z The z position.
     */
    void setZ(double z);

    /**
     * Getter for the z position.
     * @return The z position.
     */
    double getZ();

    /**
     * Getter for the block z position.
     * @return The block z position.
     */
    double getBlockZ();

    /**
     * Setter for the yaw.
     * @param yaw The yaw.
     */
    void setYaw(float yaw);

    /**
     * Getter for the yaw.
     * @return The yaw.
     */
    float getYaw();

    /**
     * Setter for the pitch.
     * @param pitch The pitch.
     */
    void setPitch(float pitch);

    /**
     * Getter for the pitch.
     * @return The pitch.
     */
    float getPitch();

    /**
     * Getter for the block position.
     * @return The block position.
     */
    Position getBlockPosition();

    /**
     * Setter for the world.
     * @param world The world.
     */
    void setWorld(String world);

    /**
     * Getter for the world.
     * @return The world.
     */
    String getWorld();
}
