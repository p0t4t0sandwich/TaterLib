/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.forge.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_12_2.forge.event.entity.ForgeEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_12_2.forge.event.entity.ForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_12_2.forge.event.entity.ForgeEntitySpawnEvent;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Listens for entity events. */
public class ForgeEntityListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onEntityDamage(LivingDamageEvent event) {
        EntityEvents.DAMAGE.invoke(new ForgeEntityDamageEvent(event));
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
        EntityEvents.SPAWN.invoke(new ForgeEntitySpawnEvent(event));
    }
}
