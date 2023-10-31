package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.entity.AbstractEntity;

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
