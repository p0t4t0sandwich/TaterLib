package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;

/**
 * Abstract class for an entity event.
 */
public interface EntityEvent {
    /**
     * Gets the entity involved in this event.
     * @return The entity.
     */
    Entity getEntity();
}
