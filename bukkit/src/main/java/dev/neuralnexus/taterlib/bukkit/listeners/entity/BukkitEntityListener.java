package dev.neuralnexus.taterlib.bukkit.listeners.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDamageEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

/**
 * Listens for entity events.
 */
public class BukkitEntityListener implements Listener {
    /**
     * Called when an entity is damaged.
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        EntityEvents.DAMAGE.invoke(new BukkitEntityDamageEvent(event));
    }

    /**
     * Called when an entity is damaged by an entity.
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        EntityEvents.DAMAGE_BY_ENTITY.invoke(new BukkitEntityDamageEvent.BukkitEntityDamageByEntityEvent(event));
    }

    /**
     * Called when an entity is damaged by a block.
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        EntityEvents.DAMAGE_BY_BLOCK.invoke(new BukkitEntityDamageEvent.BukkitEntityDamageByBlockEvent(event));
    }

    /**
     * Called when an entity dies.
     * @param event The event.
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityEvents.DEATH.invoke(new BukkitEntityDeathEvent(event));
    }

    /**
     * Called when an entity spawns.
     * @param event The event.
     */
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityEvents.SPAWN.invoke(new BukkitEntitySpawnEvent(event));
    }
}
