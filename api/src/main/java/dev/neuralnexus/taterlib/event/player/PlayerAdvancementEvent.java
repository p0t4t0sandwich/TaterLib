/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.player;

import java.util.Collection;

/** Abstract class for player advancement events */
public interface PlayerAdvancementEvent extends PlayerEvent {
    /**
     * Gets the advancement TODO: Abstract advancements further
     *
     * @return The advancement
     */
    String advancement();

    /** Abstract class for player advancement done events */
    interface AdvancementFinished extends PlayerAdvancementEvent {}

    /** Abstract class for player advancement progress events */
    interface AdvancementProgress extends PlayerAdvancementEvent {
        /**
         * Gets the criterion
         *
         * @return The criterion
         */
        Collection<String> criterion();
    }
}
