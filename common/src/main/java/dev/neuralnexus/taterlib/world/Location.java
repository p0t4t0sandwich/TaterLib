package dev.neuralnexus.taterlib.world;

/** Abstracts a location. */
public interface Location {
    /**
     * Getter for the x position.
     *
     * @return The x position.
     */
    default double x() {
        return blockPosition().x();
    }

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
    default double blockX() {
        return Math.floor(x());
    }

    /**
     * Getter for the y position.
     *
     * @return The y position.
     */
    default double y() {
        return blockPosition().y();
    }

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
    default double blockY() {
        return Math.floor(y());
    }

    /**
     * Getter for the z position.
     *
     * @return The z position.
     */
    default double z() {
        return blockPosition().z();
    }

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
    default double blockZ() {
        return Math.floor(z());
    }

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
    BlockPos blockPosition();

    /**
     * Getter for the world.
     *
     * @return The world.
     */
    World world();

    /**
     * Setter for the world.
     *
     * @param world The world.
     */
    void setWorld(World world);

    /**
     * Getter for the dimension.
     *
     * @return The dimension.
     */
    default String dimension() {
        return world().dimension();
    }

    /**
     * Get the distance between two locations.
     *
     * @param location The location to compare to.
     * @return The distance between the two locations.
     */
    default double distance(Location location) {
        return distance(location.x(), location.y(), location.z());
    }

    /**
     * Get the distance between two locations.
     *
     * @param x The x position to compare to.
     * @param y The y position to compare to.
     * @param z The z position to compare to.
     * @return The distance between the two locations.
     */
    default double distance(double x, double y, double z) {
        double x1 = x() - x;
        double y1 = y() - y;
        double z1 = z() - z;
        return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
    }

    /**
     * Builder for the location.
     */
    interface Builder {
        /**
         * Use an existing location to build from.
         *
         * @param location The location.
         */
        Builder from(Location location);

        /**
         * Set the position.
         *
         * @param blockPos The position.
         */
        Builder position(BlockPos blockPos);

        /**
         * Set the position.
         *
         * @param x The x position.
         * @param y The y position.
         * @param z The z position.
         */
        default Builder position(double x, double y, double z) {
            return position(new BlockPos(x, y, z));
        }

        /**
         * Set the yaw.
         *
         * @param yaw The yaw.
         * @return The builder.
         */
        Builder yaw(float yaw);

        /**
         * Set the pitch.
         *
         * @param pitch The pitch.
         * @return The builder.
         */
        Builder pitch(float pitch);

        /**
         * Set the world.
         *
         * @param world The world.
         * @return The builder.
         */
        Builder world(World world);

        /**
         * Build the location.
         *
         * @return The location.
         */
        Location build();
    }
}
