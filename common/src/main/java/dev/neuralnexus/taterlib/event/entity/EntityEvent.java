package dev.neuralnexus.taterlib.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.Event;

/** Abstract class for an entity event. */
public interface EntityEvent extends Event {
    /**
     * Gets the entity involved in this event.
     *
     * @return The entity.
     */
    Entity getEntity();
}
