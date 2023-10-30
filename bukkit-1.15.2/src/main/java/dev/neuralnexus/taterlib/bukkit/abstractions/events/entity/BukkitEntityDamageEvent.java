package dev.neuralnexus.taterlib.bukkit.abstractions.events.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.entity.BukkitEntity;
import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDamageEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Bukkit implementation of {@link AbstractEntityDamageEvent}.
 */
public class BukkitEntityDamageEvent extends BukkitEntityEvent implements AbstractEntityDamageEvent {
    private final EntityDamageEvent event;

    public BukkitEntityDamageEvent(EntityDamageEvent event) {
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
        return event.getDamage();
    }

    /**
     * Bukkit implementation of {@link AbstractEntityDamageByBlockEvent}.
     */
    public static class BukkitEntityDamageByEntityEvent extends BukkitEntityDamageEvent implements AbstractEntityDamageByEntityEvent {
        private final EntityDamageByEntityEvent event;

        public BukkitEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public AbstractEntity getDamager() {
            return new BukkitEntity(event.getDamager());
        }
    }

    /**
     * Bukkit implementation of {@link AbstractEntityDamageByBlockEvent}.
     */
    public static class BukkitEntityDamageByBlockEvent extends BukkitEntityDamageEvent implements AbstractEntityDamageByBlockEvent {
        private final EntityDamageByBlockEvent event;

        public BukkitEntityDamageByBlockEvent(EntityDamageByBlockEvent event) {
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
