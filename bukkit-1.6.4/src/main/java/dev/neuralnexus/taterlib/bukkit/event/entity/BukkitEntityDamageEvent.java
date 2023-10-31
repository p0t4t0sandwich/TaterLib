package dev.neuralnexus.taterlib.bukkit.event.entity;

import dev.neuralnexus.taterlib.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.lang.reflect.Field;

/**
 * Bukkit implementation of {@link EntityDamageEvent}.
 */
public class BukkitEntityDamageEvent extends BukkitEntityEvent implements EntityDamageEvent {
    private final org.bukkit.event.entity.EntityDamageEvent event;

    public BukkitEntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCause() {
        return event.getCause().name();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getDamage() {
        // Method is ambiguous, time to reflect!
        double damage = 0.0;
        try {
            Field damageField = event.getClass().getDeclaredField("damage");
            damageField.setAccessible(true);
            damage = damageField.getDouble(event);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        return damage;
    }

    /**
     * Bukkit implementation of {@link DamageByBlock}.
     */
    public static class BukkitEntityDamageByEntity extends BukkitEntityDamageEvent implements DamageByEntity {
        private final EntityDamageByEntityEvent event;

        public BukkitEntityDamageByEntity(EntityDamageByEntityEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Entity getDamager() {
            return new BukkitEntity(event.getDamager());
        }
    }

    /**
     * Bukkit implementation of {@link DamageByBlock}.
     */
    public static class BukkitEntityDamageByBlock extends BukkitEntityDamageEvent implements DamageByBlock {
        private final EntityDamageByBlockEvent event;

        public BukkitEntityDamageByBlock(EntityDamageByBlockEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String getDamager() {
            return event.getDamager().getType().name();
        }
    }
}
