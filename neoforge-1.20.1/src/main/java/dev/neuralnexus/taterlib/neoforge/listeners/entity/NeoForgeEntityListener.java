package dev.neuralnexus.taterlib.neoforge.listeners.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.entity.NeoForgeEntityDamageEvent;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.entity.NeoForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.entity.NeoForgeEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
