package dev.neuralnexus.taterlib.common.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.AbstractCancellableEvent;

/**
 * Abstract class for an entity damage event.
 */
public interface AbstractEntityDamageEvent extends AbstractEntityEvent, AbstractCancellableEvent {
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
    interface AbstractEntityDamageByEntityEvent extends AbstractEntityDamageEvent {
        /**
         * Gets the damager.
         * @return The damager.
         */
        AbstractEntity getDamager();
    }

    /**
     * Abstract class for an entity damage by block event.
     */
    interface AbstractEntityDamageByBlockEvent extends AbstractEntityDamageEvent {
        /**
         * Gets the block damager.
         * TODO: Abstract block.
         * @return The block damager.
         */
        String getDamager();
    }
}
