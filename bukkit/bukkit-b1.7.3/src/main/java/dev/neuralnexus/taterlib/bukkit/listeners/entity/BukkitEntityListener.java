package dev.neuralnexus.taterlib.bukkit.listeners.entity;

import dev.neuralnexus.taterlib.bukkit.event.entity.BukkitEntityDamageEvent;
import dev.neuralnexus.taterlib.bukkit.event.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.event.entity.BukkitEntitySpawnEvent;
import dev.neuralnexus.taterlib.bukkit.event.player.BukkitPlayerDeathEvent;
import dev.neuralnexus.taterlib.common.event.api.EntityEvents;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;

/** Listens for entity events. */
public class BukkitEntityListener extends EntityListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The event.
     */
    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        EntityEvents.DAMAGE.invoke(new BukkitEntityDamageEvent(event));
        switch (event.getCause()) {
            case ENTITY_ATTACK:
                EntityEvents.DAMAGE_BY_ENTITY.invoke(
                        new BukkitEntityDamageEvent.DamageByEntity(
                                (EntityDamageByEntityEvent) event));
                break;
            case SUFFOCATION:
                EntityEvents.DAMAGE_BY_BLOCK.invoke(
                        new BukkitEntityDamageEvent.DamageByBlock(
                                (EntityDamageByBlockEvent) event));
                break;
        }
    }

    //    /**
    //     * Called when an entity is damaged by an entity.
    //     * @param event The event.
    //     */
    //    @EventHandler
    //    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    //        EntityListener.onEntityDamageByEntity(new
    // BukkitEntityDamageEvent.DamageByEntity(event));
    //    }
    //
    //    /**
    //     * Called when an entity is damaged by a block.
    //     * @param event The event.
    //     */
    //    @EventHandler
    //    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
    //        EntityListener.onEntityDamageByBlock(new
    // BukkitEntityDamageEvent.DamageByBlock(event));
    //    }

    /**
     * Called when an entity dies.
     *
     * @param event The event.
     */
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        EntityEvents.DEATH.invoke(new BukkitEntityDeathEvent(event));

        // Check if the entity is a player
        if (event.getEntity() instanceof Player) {
            PlayerEvents.DEATH.invoke(new BukkitPlayerDeathEvent(event));
        }
    }

    /**
     * Called when an entity spawns.
     *
     * @param event The event.
     */
    @Override
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        EntityEvents.SPAWN.invoke(new BukkitEntitySpawnEvent(event));
    }
}
