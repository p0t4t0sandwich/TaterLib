/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event;

/** Abstract class for cancellable events. */
public interface Cancellable {
    /**
     * Gets whether the event is cancelled.
     *
     * @return Whether the event is cancelled.
     */
    boolean cancelled();

    /**
     * Sets whether the event is cancelled.
     *
     * @param cancelled Whether the event is cancelled.
     */
    void setCancelled(boolean cancelled);
}
