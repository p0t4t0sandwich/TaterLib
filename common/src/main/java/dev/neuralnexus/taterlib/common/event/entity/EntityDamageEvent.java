package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.Cancellable;

/**
 * Abstract class for an entity damage event.
 */
public interface EntityDamageEvent extends EntityEvent, Cancellable {
    /**
     * Gets the damage cause.
     * TODO: Abstract damage cause.
     * @return The damage cause.
     */
    String getCause();

    /**
     * Gets the damage.
     * @return The damage.
     */
    double getDamage();

    /**
     * Abstract class for an entity damage by entity event.
     */
    interface DamageByEntity extends EntityDamageEvent {
        /**
         * Gets the damager.
         * @return The damager.
         */
        Entity getDamager();
    }

    /**
     * Abstract class for an entity damage by block event.
     */
    interface DamageByBlock extends EntityDamageEvent {
        /**
         * Gets the block damager.
         * TODO: Abstract block.
         * @return The block damager.
         */
        String getDamager();
    }
}
