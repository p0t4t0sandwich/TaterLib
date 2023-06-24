package dev.neuralnexus.taterapi.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;

/**
 * This is a Fabric event that is fired when a player receives an advancement.
 */
public interface FabricPlayerAdvancementEvent {
    Event<FabricPlayerAdvancementEvent> EVENT = EventFactory.createArrayBacked(FabricPlayerAdvancementEvent.class, (listeners) -> (player, advancement) -> {
        for (FabricPlayerAdvancementEvent listener : listeners) {
            listener.onPlayerAdvancement(player, advancement);
        }
    });

    void onPlayerAdvancement(PlayerEntity player, Advancement advancement);
}
