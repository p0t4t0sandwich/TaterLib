/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event;

/** Generic event interface. */
public interface Event {
    /** Gets the event name. */
    default String name() {
        return this.getClass().getSimpleName();
    }
}
