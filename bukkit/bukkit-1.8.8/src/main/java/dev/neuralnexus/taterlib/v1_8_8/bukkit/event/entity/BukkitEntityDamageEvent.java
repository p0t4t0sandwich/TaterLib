/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_8.bukkit.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.entity.BukkitEntity;

import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/** Bukkit implementation of {@link EntityDamageEvent}. */
public class BukkitEntityDamageEvent extends BukkitEntityEvent implements EntityDamageEvent {
    private final org.bukkit.event.entity.EntityDamageEvent event;

    public BukkitEntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String cause() {
        return event.getCause().name();
    }

    /** {@inheritDoc} */
    @Override
    public double damage() {
        return event.getDamage(org.bukkit.event.entity.EntityDamageEvent.DamageModifier.BASE);
    }

    /** Bukkit implementation of {@link EntityDamageEvent.DamageByEntity}. */
    public static class DamageByEntity extends BukkitEntityDamageEvent
            implements EntityDamageEvent.DamageByEntity {
        private final EntityDamageByEntityEvent event;

        public DamageByEntity(EntityDamageByEntityEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public Entity damager() {
            return new BukkitEntity(event.getDamager());
        }
    }

    /** Bukkit implementation of {@link EntityDamageEvent.DamageByBlock}. */
    public static class DamageByBlock extends BukkitEntityDamageEvent
            implements EntityDamageEvent.DamageByBlock {
        private final EntityDamageByBlockEvent event;

        public DamageByBlock(EntityDamageByBlockEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public String damager() {
            return event.getDamager().getType().name();
        }
    }
}
