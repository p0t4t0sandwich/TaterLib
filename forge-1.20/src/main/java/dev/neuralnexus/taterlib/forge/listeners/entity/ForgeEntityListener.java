package dev.neuralnexus.taterlib.forge.listeners.entity;

import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import dev.neuralnexus.taterlib.forge.abstrations.events.entity.ForgeEntityDamageEvent;
import dev.neuralnexus.taterlib.forge.abstrations.events.entity.ForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.forge.abstrations.events.entity.ForgeEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for entity events.
 */
public class ForgeEntityListener {
    /**
     * Called when an entity is damaged.
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onEntityDamage(LivingDamageEvent event) {
        EntityListener.onEntityDamage(new ForgeEntityDamageEvent(event));
    }

    /**
     * Called when an entity dies.
     * @param event The entity death event
     */
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        EntityListener.onEntityDeath(new ForgeEntityDeathEvent(event));
    }

    /**
     * Called when an entity is spawned.
     * @param event The entity spawn event
     */
    @SubscribeEvent
    public void onEntitySpawn(MobSpawnEvent.FinalizeSpawn event) {
        EntityListener.onEntitySpawn(new ForgeEntitySpawnEvent(event));
    }
}
