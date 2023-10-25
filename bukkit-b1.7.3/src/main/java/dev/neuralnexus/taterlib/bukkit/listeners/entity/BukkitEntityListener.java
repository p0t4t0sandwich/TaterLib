package dev.neuralnexus.taterlib.bukkit.listeners.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDamageEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class BukkitEntityListener extends org.bukkit.event.entity.EntityListener {
    /**
     * Called when an entity is damaged.
     * @param event The event.
     */
    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        EntityListener.onEntityDamage(new BukkitEntityDamageEvent(event));
    }

    /**
     * Called when an entity is damaged by an entity.
     * @param event The event.
     */
//    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        EntityListener.onEntityDamageByEntity(new BukkitEntityDamageEvent.BukkitEntityDamageByEntityEvent(event));
    }

    /**
     * Called when an entity is damaged by a block.
     * @param event The event.
     */
//    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        EntityListener.onEntityDamageByBlock(new BukkitEntityDamageEvent.BukkitEntityDamageByBlockEvent(event));
    }

    /**
     * Called when an entity dies.
     * @param event The event.
     */
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        EntityListener.onEntityDeath(new BukkitEntityDeathEvent(event));
    }
}
