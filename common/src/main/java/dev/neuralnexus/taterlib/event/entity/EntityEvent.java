package dev.neuralnexus.taterlib.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;

/** Abstract class for an entity event. */
public interface EntityEvent {
    /**
     * Gets the entity involved in this event.
     *
     * @return The entity.
     */
    Entity getEntity();
}
