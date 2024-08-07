/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_19.forge.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterlib.forge.utils.modern.event.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntitySpawnEvent;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Listens for entity events. */
public class ForgeEntityListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onEntityDamage(LivingDamageEvent event) {
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        event.getEntity(),
                        event.getSource(),
                        event.getAmount(),
                        new ForgeCancellableEventWrapper(event)));
    }

    /**
     * Called when an entity dies.
     *
     * @param event The entity death event
     */
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        EntityEvents.DEATH.invoke(
                new VanillaEntityDeathEvent(event.getEntity(), event.getSource()));
    }

    /**
     * Called when an entity is spawned.
     *
     * @param event The entity spawn event
     */
    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        EntityEvents.SPAWN.invoke(
                new VanillaEntitySpawnEvent(
                        event.getEntity(), new ForgeCancellableEventWrapper(event)));
    }
}
