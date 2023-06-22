package dev.neuralnexus.taterapi.fabric.events.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

/**
 * This is a Fabric event that is fired when the server is started.
 */
public interface FabricServerStartedEvent {
    Event<FabricServerStartedEvent> EVENT = EventFactory.createArrayBacked(FabricServerStartedEvent.class, (listeners) -> (MinecraftServer server) -> {
        for (FabricServerStartedEvent listener : listeners) {
            listener.onServerStarted(server);
        }
    });

    void onServerStarted(MinecraftServer server);
}
