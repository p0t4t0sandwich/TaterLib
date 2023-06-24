package dev.neuralnexus.taterapi.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;

/**
 * This is a Fabric event that is fired when a player finishes an advancement.
 */
public interface FabricPlayerAdvancementFinishedEvent {
    Event<FabricPlayerAdvancementFinishedEvent> EVENT = EventFactory.createArrayBacked(FabricPlayerAdvancementFinishedEvent.class, (listeners) -> (player, advancement) -> {
        for (FabricPlayerAdvancementFinishedEvent listener : listeners) {
            listener.onPlayerAdvancementFinished(player, advancement);
        }
    });

    void onPlayerAdvancementFinished(PlayerEntity player, Advancement advancement);
}
