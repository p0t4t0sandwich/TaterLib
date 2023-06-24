package dev.neuralnexus.taterapi.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * This is a Fabric event that is fired when a player sends a message.
 */
public interface FabricPlayerMessageEvent {
    Event<FabricPlayerMessageEvent> EVENT = EventFactory.createArrayBacked(FabricPlayerMessageEvent.class, (listeners) -> (player, message, isCanceled) -> {
        for (FabricPlayerMessageEvent listener : listeners) {
            listener.onPlayerMessage(player, message, isCanceled);
        }
    });

    void onPlayerMessage(ServerPlayerEntity player, String message, boolean isCanceled);
}
