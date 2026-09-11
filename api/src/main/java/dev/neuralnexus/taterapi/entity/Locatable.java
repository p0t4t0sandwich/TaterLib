/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;

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
    default @NonNull Level level() {
        return this.location().level();
    }

    /**
     * Get the current biome of the object
     *
     * @return The current biome of the object
     */
    // TODO: Defer to Level through Level#biome(Location)
    // TODO: some backwards-compatible translation to modern namespaces
    @NonNull Identifier biome();

    /**
     * Get the current dimension of the object
     *
     * @return The current dimension of the object
     */
    default @NonNull Identifier dimension() {
        return this.location().level().dimension();
    }

    /**
     * Get the position of the object
     *
     * @return The position of the object
     */
    default @NonNull BlockPos pos() {
        return this.location().pos();
    }
}
