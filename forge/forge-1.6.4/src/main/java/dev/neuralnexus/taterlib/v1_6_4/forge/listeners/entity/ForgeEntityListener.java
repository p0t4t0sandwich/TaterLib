/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_6_4.forge.event.entity.ForgeEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_6_4.forge.event.entity.ForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_6_4.forge.event.entity.ForgeEntitySpawnEvent;

import net.minecraftforge.event.ForgeSubscribe;
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
    @ForgeSubscribe
    public void onEntityDamage(LivingAttackEvent event) {
        EntityEvents.DAMAGE.invoke(new ForgeEntityDamageEvent(event));
    }

    /**
     * Called when an entity dies.
     *
     * @param event The entity death event
     */
    @ForgeSubscribe
    public void onEntityDeath(LivingDeathEvent event) {
        EntityEvents.DEATH.invoke(new ForgeEntityDeathEvent(event));
    }

    /**
     * Called when an entity is spawned.
     *
     * @param event The entity spawn event
     */
    @ForgeSubscribe
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        EntityEvents.SPAWN.invoke(new ForgeEntitySpawnEvent(event));
    }
}
