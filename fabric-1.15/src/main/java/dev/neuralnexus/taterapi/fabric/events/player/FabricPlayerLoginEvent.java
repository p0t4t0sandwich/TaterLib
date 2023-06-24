package dev.neuralnexus.taterapi.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * This is a Fabric event that is fired when a player logs in.
 */
public interface FabricPlayerLoginEvent {
    Event<FabricPlayerLoginEvent> EVENT = EventFactory.createArrayBacked(FabricPlayerLoginEvent.class, (listeners) -> (player) -> {
        for (FabricPlayerLoginEvent listener : listeners) {
            listener.onPlayerLogin(player);
        }
    });

    void onPlayerLogin(ServerPlayerEntity player);
}
