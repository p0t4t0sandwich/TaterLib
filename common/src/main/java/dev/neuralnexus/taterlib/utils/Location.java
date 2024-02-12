package dev.neuralnexus.taterlib.utils;

/** Abstracts a location. */
public interface Location {
    // TODO: Abstract world

    /**
     * Getter for the x position.
     *
     * @return The x position.
     */
    double x();

    /**
     * Setter for the x position.
     *
     * @param x The x position.
     */
    void setX(double x);

    /**
     * Getter for the block x position.
     *
     * @return The block x position.
     */
    double blockX();

    /**
     * Getter for the y position.
     *
     * @return The y position.
     */
    double y();

    /**
     * Setter for the y position.
     *
     * @param y The y position.
     */
    void setY(double y);

    /**
     * Getter for the block y position.
     *
     * @return The block y position.
     */
    double blockY();

    /**
     * Getter for the z position.
     *
     * @return The z position.
     */
    double z();

    /**
     * Setter for the z position.
     *
     * @param z The z position.
     */
    void setZ(double z);

    /**
     * Getter for the block z position.
     *
     * @return The block z position.
     */
    double blockZ();

    /**
     * Getter for the yaw.
     *
     * @return The yaw.
     */
    float yaw();

    /**
     * Setter for the yaw.
     *
     * @param yaw The yaw.
     */
    void setYaw(float yaw);

    /**
     * Getter for the pitch.
     *
     * @return The pitch.
     */
    float pitch();

    /**
     * Setter for the pitch.
     *
     * @param pitch The pitch.
     */
    void setPitch(float pitch);

    /**
     * Getter for the block position.
     *
     * @return The block position.
     */
    Position blockPosition();

    /**
     * Getter for the world.
     *
     * @return The world.
     */
    String world();

    /**
     * Setter for the world.
     *
     * @param world The world.
     */
    void setWorld(String world);
}
