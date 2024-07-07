/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event.entity;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.world.Location;

/** Abstract class for an entity spawn event. */
public interface EntitySpawnEvent extends EntityEvent, Cancellable {
    /**
     * Gets the location of the entity.
     *
     * @return The location of the entity.
     */
    Location location();
}
