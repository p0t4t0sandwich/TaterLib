package dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.entity;

import dev.neuralnexus.taterlib.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.entity.BukkitEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.entity.BukkitEntitySpawnEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

/** Listens for entity events. */
public class BukkitEntityListener implements Listener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        EntityEvents.DAMAGE.invoke(new BukkitEntityDamageEvent(event));
    }

    /**
     * Called when an entity is damaged by an entity.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        EntityEvents.DAMAGE_BY_ENTITY.invoke(new BukkitEntityDamageEvent.DamageByEntity(event));
    }

    /**
     * Called when an entity is damaged by a block.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        EntityEvents.DAMAGE_BY_BLOCK.invoke(new BukkitEntityDamageEvent.DamageByBlock(event));
    }

    /**
     * Called when an entity dies.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityEvents.DEATH.invoke(new BukkitEntityDeathEvent(event));
    }

    /**
     * Called when an entity spawns.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        EntityEvents.SPAWN.invoke(new BukkitEntitySpawnEvent(event));
    }
}
