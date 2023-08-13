package dev.neuralnexus.taterlib.neoforge.listeners.entity;

import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import dev.neuralnexus.taterlib.neoforge.abstractions.entity.NeoForgeEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for entity events.
 */
public class NeoForgeEntityListener {
    /**
     * Called when an entity dies.
     * @param event The entity death event
     */
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        EntityListener.onEntityDeath(new NeoForgeEntity(entity), event.getSource().getLocalizedDeathMessage(entity).getString());
    }

    /**
     * Called when an entity is spawned.
     * @param event The entity spawn event
     */
    @SubscribeEvent
    public void onEntitySpawn(MobSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        EntityListener.onEntitySpawn(new NeoForgeEntity(entity));
    }
}
