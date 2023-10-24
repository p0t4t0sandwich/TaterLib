package dev.neuralnexus.taterlib.common.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.events.AbstractCancellableEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;

/**
 * Abstract class for an entity spawn event.
 */
public interface AbstractEntitySpawnEvent extends AbstractEntityEvent, AbstractCancellableEvent {
    /**
     * Gets the location of the entity.
     * @return The location of the entity.
     */
    Location getLocation();
}
