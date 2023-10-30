package dev.neuralnexus.taterlib.bukkit.listeners.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDamageEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;
import org.bukkit.event.entity.*;

/**
 * Listens for entity events.
 */
public class BukkitEntityListener extends EntityListener {
    /**
     * Called when an entity is damaged.
     * @param event The event.
     */
    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        EntityEvents.DAMAGE.invoke(new BukkitEntityDamageEvent(event));
        switch (event.getCause()) {
            case ENTITY_ATTACK:
                EntityEvents.DAMAGE_BY_ENTITY.invoke(new BukkitEntityDamageEvent.BukkitEntityDamageByEntityEvent((EntityDamageByEntityEvent) event));
                break;
            case SUFFOCATION:
                EntityEvents.DAMAGE_BY_BLOCK.invoke(new BukkitEntityDamageEvent.BukkitEntityDamageByBlockEvent((EntityDamageByBlockEvent) event));
                break;
        }
    }

//    /**
//     * Called when an entity is damaged by an entity.
//     * @param event The event.
//     */
//    @EventHandler
//    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
//        EntityListener.onEntityDamageByEntity(new BukkitEntityDamageEvent.BukkitEntityDamageByEntityEvent(event));
//    }
//
//    /**
//     * Called when an entity is damaged by a block.
//     * @param event The event.
//     */
//    @EventHandler
//    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
//        EntityListener.onEntityDamageByBlock(new BukkitEntityDamageEvent.BukkitEntityDamageByBlockEvent(event));
//    }

    /**
     * Called when an entity dies.
     * @param event The event.
     */
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        EntityEvents.DEATH.invoke(new BukkitEntityDeathEvent(event));
    }

    /**
     * Called when an entity spawns.
     * @param event The event.
     */
    @Override
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        EntityEvents.SPAWN.invoke(new BukkitEntitySpawnEvent(event));
    }
}
