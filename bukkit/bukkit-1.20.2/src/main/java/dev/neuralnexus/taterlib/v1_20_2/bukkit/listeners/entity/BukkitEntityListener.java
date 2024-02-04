package dev.neuralnexus.taterlib.v1_20_2.bukkit.listeners.entity;

import dev.neuralnexus.taterlib.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.entity.VanillaEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.entity.VanillaEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.entity.VanillaEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_20_2.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20_2.bukkit.event.BukkitCancellableEventWrapper;

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
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        BukkitAdapter.get().getEntity(event.getEntity()),
                        BukkitAdapter.get().getLastDamageSource(event.getEntity()),
                        (float) event.getDamage(),
                        new BukkitCancellableEventWrapper<>(event)));
    }

    /**
     * Called when an entity is damaged by an entity.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // TODO: Branch into own event EntityEvents.DAMAGE_BY_ENTITY
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        BukkitAdapter.get().getEntity(event.getEntity()),
                        BukkitAdapter.get().getLastDamageSource(event.getEntity()),
                        (float) event.getDamage(),
                        new BukkitCancellableEventWrapper<>(event)));
    }

    /**
     * Called when an entity is damaged by a block.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        // TODO: Branch into own event EntityEvents.DAMAGE_BY_BLOCK
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        BukkitAdapter.get().getEntity(event.getEntity()),
                        BukkitAdapter.get().getLastDamageSource(event.getEntity()),
                        (float) event.getDamage(),
                        new BukkitCancellableEventWrapper<>(event)));
    }

    /**
     * Called when an entity dies.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityEvents.DEATH.invoke(
                new VanillaEntityDeathEvent(
                        BukkitAdapter.get().getEntity(event.getEntity()),
                        BukkitAdapter.get().getLastDamageSource(event.getEntity())));
    }

    /**
     * Called when an entity spawns.
     *
     * @param event The event.
     */
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityEvents.SPAWN.invoke(
                new VanillaEntitySpawnEvent(
                        BukkitAdapter.get().getEntity(event.getEntity()),
                        new BukkitCancellableEventWrapper<>(event)));
    }
}
