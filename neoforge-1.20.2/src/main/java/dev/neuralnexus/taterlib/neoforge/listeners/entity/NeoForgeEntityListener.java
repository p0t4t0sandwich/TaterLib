package dev.neuralnexus.taterlib.neoforge.listeners.entity;

import dev.neuralnexus.taterlib.common.event.api.EntityEvents;
import dev.neuralnexus.taterlib.neoforge.event.api.entity.NeoForgeEntityDamageEvent;
import dev.neuralnexus.taterlib.neoforge.event.api.entity.NeoForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.neoforge.event.api.entity.NeoForgeEntitySpawnEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

/**
 * Listens for entity events.
 */
public class NeoForgeEntityListener {
    /**
     * Called when an entity is damaged.
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onEntityDamage(LivingDamageEvent event) {
        EntityEvents.DAMAGE.invoke(new NeoForgeEntityDamageEvent(event));
    }

    /**
     * Called when an entity dies.
     * @param event The entity death event
     */
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        EntityEvents.DEATH.invoke(new NeoForgeEntityDeathEvent(event));
    }

    /**
     * Called when an entity is spawned.
     * @param event The entity spawn event
     */
    @SubscribeEvent
    public void onEntitySpawn(MobSpawnEvent.FinalizeSpawn event) {
        EntityEvents.SPAWN.invoke(new NeoForgeEntitySpawnEvent(event));
    }
}
