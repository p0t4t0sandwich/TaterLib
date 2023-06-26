package dev.neuralnexus.taterapi.fabric.events.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;

/**
 * This is a Fabric event that is fired when an entity is spawned.
 */
public interface FabricEntitySpawnEvent {
    Event<FabricEntitySpawnEvent> EVENT = EventFactory.createArrayBacked(FabricEntitySpawnEvent.class, (listeners) -> (entity) -> {
        for (FabricEntitySpawnEvent listener : listeners) {
            listener.onEntitySpawn(entity);
        }
    });

    void onEntitySpawn(Entity entity);
}
