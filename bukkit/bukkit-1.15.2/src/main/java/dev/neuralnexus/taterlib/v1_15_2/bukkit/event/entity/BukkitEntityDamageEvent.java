/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.BukkitEntity;

import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

/** Bukkit implementation of {@link EntityDamageEvent}. */
public class BukkitEntityDamageEvent extends BukkitEntityEvent implements EntityDamageEvent {
    private final org.bukkit.event.entity.EntityDamageEvent event;

    public BukkitEntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public String cause() {
        return event.getCause().name();
    }

    @Override
    public double damage() {
        return event.getDamage();
    }

    /** Bukkit implementation of {@link EntityDamageEvent.DamageByEntity}. */
    public static class DamageByEntity extends BukkitEntityDamageEvent
            implements EntityDamageEvent.DamageByEntity {
        private final EntityDamageByEntityEvent event;

        public DamageByEntity(EntityDamageByEntityEvent event) {
            super(event);
            this.event = event;
        }

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

        @Override
        public String damager() {
            Objects.requireNonNull(event.getDamager());
            return event.getDamager().getType().name();
        }
    }
}
