/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.Cancellable;

/** Abstract class for an entity damage event. */
public interface EntityDamageEvent extends EntityEvent, Cancellable {
    /**
     * Gets the damage cause. TODO: Abstract damage cause.
     *
     * @return The damage cause.
     */
    String cause();

    /**
     * Gets the damage.
     *
     * @return The damage.
     */
    double damage();

    /** Abstract class for an entity damage by entity event. */
    interface DamageByEntity extends EntityDamageEvent {
        /**
         * Gets the damager.
         *
         * @return The damager.
         */
        Entity damager();
    }

    /** Abstract class for an entity damage by block event. */
    interface DamageByBlock extends EntityDamageEvent {
        /**
         * Gets the block damager. TODO: Abstract block.
         *
         * @return The block damager.
         */
        String damager();
    }
}
