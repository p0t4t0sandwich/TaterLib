/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.listeners.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_7_10.forge.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.entity.ForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity.VanillaEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity.VanillaEntitySpawnEvent;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

/** Listens for entity events. */
public class ForgeEntityListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onEntityDamage(LivingAttackEvent event) {
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        event.entityLiving,
                        event.source,
                        event.entityLiving.lastHealth,
                        event.ammount,
                        new ForgeCancellableEventWrapper(event)));
    }

    /**
     * Called when an entity dies.
     *
     * @param event The entity death event
     */
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        EntityEvents.DEATH.invoke(new ForgeEntityDeathEvent(event));
    }

    /**
     * Called when an entity is spawned.
     *
     * @param event The entity spawn event
     */
    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        EntityEvents.SPAWN.invoke(
                new VanillaEntitySpawnEvent(event.entity, new ForgeCancellableEventWrapper(event)));
    }
}
