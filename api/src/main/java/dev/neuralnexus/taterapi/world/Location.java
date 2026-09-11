/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.world;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.level.Level;

import org.jspecify.annotations.NonNull;

/** Abstracts a location. */
public interface Location {
    /**
     * Getter for the x position.
     *
     * @return The x position.
     */
    default double x() {
        return this.pos().x();
    }

    /**
     * Setter for the x position.
     *
     * @param x The x position.
     */
    void setX(final double x);

    /**
     * Getter for the block x position.
     *
     * @return The block x position.
     */
    default double blockX() {
        return this.pos().blockX();
    }

    /**
     * Getter for the y position.
     *
     * @return The y position.
     */
    default double y() {
        return this.pos().y();
    }

    /**
     * Setter for the y position.
     *
     * @param y The y position.
     */
    void setY(final double y);

    /**
     * Getter for the block y position.
     *
     * @return The block y position.
     */
    default int blockY() {
        return this.pos().blockY();
    }

    /**
     * Getter for the z position.
     *
     * @return The z position.
     */
    default double z() {
        return this.pos().z();
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
        return this.pos().blockZ();
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
    void setYaw(final float yaw);

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
    void setPitch(final float pitch);

    /**
     * Getter for the block position.
     *
     * @return The block position.
     */
    @NonNull BlockPos pos();

    @Deprecated
    default @NonNull BlockPos blockPosition() {
        return this.pos();
    }

    /**
     * Getter for the level.
     *
     * @return The level.
     */
    @NonNull Level level();

    /**
     * Setter for the level.
     *
     * @param level The level.
     */
    void setLevel(final @NonNull Level level);

    /**
     * Getter for the dimension.
     *
     * @return The dimension.
     */
    default @NonNull Identifier dimension() {
        return this.level().dimension();
    }

    /**
     * Get the distance between two locations.
     *
     * @param location The location to compare to.
     * @return The distance between the two locations.
     */
    default double distance(final @NonNull Location location) {
        return this.distance(location.x(), location.y(), location.z());
    }

    /**
     * Get the distance between two locations.
     *
     * @param x The x position to compare to.
     * @param y The y position to compare to.
     * @param z The z position to compare to.
     * @return The distance between the two locations.
     */
    default double distance(final double x, final double y, final double z) {
        double x1 = x() - x;
        double y1 = y() - y;
        double z1 = z() - z;
        return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
    }

    /**
     * Creates a new builder for locations.
     *
     * @return The builder
     */
    static Builder builder() {
        return TaterAPI.getBuilder(Builder.class);
    }

    /** Builder for the location. */
    interface Builder {
        /**
         * Use an existing location to build from.
         *
         * @param location The location.
         */
        Builder from(final @NonNull Location location);

        /**
         * Set the position.
         *
         * @param blockPos The position.
         */
        Builder position(final @NonNull BlockPos blockPos);

        /**
         * Set the position.
         *
         * @param x The x position.
         * @param y The y position.
         * @param z The z position.
         */
        default Builder position(
                final @NonNull double x, final @NonNull double y, final @NonNull double z) {
            return position(new BlockPos(x, y, z));
        }

        /**
         * Set the yaw.
         *
         * @param yaw The yaw.
         * @return The builder.
         */
        Builder yaw(final float yaw);

        /**
         * Set the pitch.
         *
         * @param pitch The pitch.
         * @return The builder.
         */
        Builder pitch(final float pitch);

        /**
         * Set the level.
         *
         * @param level The level.
         * @return The builder.
         */
        Builder level(final @NonNull Level level);

        /**
         * Build the location.
         *
         * @return The location.
         */
        @NonNull Location build();
    }
}
