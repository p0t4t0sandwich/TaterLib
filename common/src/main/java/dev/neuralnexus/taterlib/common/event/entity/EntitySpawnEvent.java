package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.utils.Location;

/**
 * Abstract class for an entity spawn event.
 */
public interface EntitySpawnEvent extends EntityEvent, Cancellable {
    /**
     * Gets the location of the entity.
     * @return The location of the entity.
     */
    Location getLocation();
}
