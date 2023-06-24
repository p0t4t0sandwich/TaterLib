package dev.neuralnexus.taterapi.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * This is a Fabric event that is fired when a player logs out.
 */
public interface FabricPlayerLogoutEvent {
    Event<FabricPlayerLogoutEvent> EVENT = EventFactory.createArrayBacked(FabricPlayerLogoutEvent.class, (listeners) -> (player) -> {
        for (FabricPlayerLogoutEvent listener : listeners) {
            listener.onPlayerLogout(player);
        }
    });

    void onPlayerLogout(ServerPlayerEntity player);
}
