package dev.neuralnexus.taterapi.fabric.events.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

/**
 * This is a Fabric event that is fired when the server is starting.
 */
public interface FabricServerStartingEvent {
    Event<FabricServerStartingEvent> EVENT = EventFactory.createArrayBacked(FabricServerStartingEvent.class, (listeners) -> (server) -> {
        for (FabricServerStartingEvent listener : listeners) {
            listener.onServerStarting(server);
        }
    });

    void onServerStarting(MinecraftServer server);
}
