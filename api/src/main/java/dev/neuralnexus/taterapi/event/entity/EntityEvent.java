/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.Event;

/** Abstract class for an entity event. */
public interface EntityEvent extends Event {
    /**
     * Gets the entity involved in this event.
     *
     * @return The entity.
     */
    Entity entity();
}
