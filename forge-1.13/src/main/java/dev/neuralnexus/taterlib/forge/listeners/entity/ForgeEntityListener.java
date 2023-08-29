package dev.neuralnexus.taterlib.forge.listeners.entity;

import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import dev.neuralnexus.taterlib.forge.abstrations.entity.ForgeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for entity events.
 */
public class ForgeEntityListener {
    /**
     * Called when an entity dies.
     * @param event The entity death event
     */
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        EntityLivingBase entity = (EntityLivingBase) event.getEntity();
        EntityListener.onEntityDeath(new ForgeEntity(entity), event.getSource().getDeathMessage(entity).getString());
    }

    /**
     * Called when an entity is spawned.
     * @param event The entity spawn event
     */
    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent event) {
        Entity entity = event.getEntity();
        EntityListener.onEntitySpawn(new ForgeEntity(entity));
    }
}
