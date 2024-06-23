package dev.neuralnexus.taterlib.event.entity;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.world.Location;

/** Abstract class for an entity spawn event. */
public interface EntitySpawnEvent extends EntityEvent, Cancellable {
    /**
     * Gets the location of the entity.
     *
     * @return The location of the entity.
     */
    Location location();
}
