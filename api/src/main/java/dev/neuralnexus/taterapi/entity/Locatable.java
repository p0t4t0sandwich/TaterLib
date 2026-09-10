/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

import org.jspecify.annotations.NonNull;

public interface Locatable extends DataHolder {
    /**
     * Get the location of the object
     *
     * @return The location of the object
     */
    @NonNull Location location();

    /**
     * Get the world of the object
     *
     * @return The world of the object
     */
    default @NonNull World level() {
        return this.location().level();
    }

    @Deprecated
    default World world() {
        return this.level();
    }

    /**
     * Get the current biome of the object
     *
     * @return The current biome of the object
     */
    // TODO: Defer to Block through Level#block(Location)
    // TODO: some backwards-compatible translation to modern namespaces
    @NonNull Identifier biome();

    /**
     * Get the current dimension of the object
     *
     * @return The current dimension of the object
     */
    default Identifier dimension() {
        return this.level().dimension();
    }

    /**
     * Get the x position of the object
     *
     * @return The x position of the object
     */
    default double x() {
        return this.location().x();
    }

    /**
     * Get the y position of the object
     *
     * @return The y position of the object
     */
    default double y() {
        return this.location().y();
    }

    /**
     * Get the z position of the object
     *
     * @return The z position of the object
     */
    default double z() {
        return this.location().z();
    }

    /**
     * Get the yaw of the object
     *
     * @return The yaw of the object
     */
    default float yaw() {
        return this.location().yaw();
    }

    /**
     * Get the pitch of the object
     *
     * @return The pitch of the object
     */
    default float pitch() {
        return this.location().pitch();
    }
}
