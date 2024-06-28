/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.b1_7_3.bukkit.event.entity;

import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;

import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.lang.reflect.Field;

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
        // Method is ambiguous, time to reflect!
        double damage = 0.0;
        try {
            Field damageField = event.getClass().getDeclaredField("damage");
            damageField.setAccessible(true);
            damage = damageField.getDouble(event);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return damage;
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
