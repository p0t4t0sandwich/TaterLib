package dev.neuralnexus.taterlib.common.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;

/**
 * Abstract class for an entity event.
 */
public interface AbstractEntityEvent {
    /**
     * Gets the entity involved in this event.
     * @return The entity.
     */
    AbstractEntity getEntity();
}
