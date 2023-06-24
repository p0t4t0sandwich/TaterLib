package dev.neuralnexus.taterapi.fabric.events.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

/**
 * This is a Fabric event that is fired when the server is stopped.
 */
public interface FabricServerStoppedEvent {
    Event<FabricServerStoppedEvent> EVENT = EventFactory.createArrayBacked(FabricServerStoppedEvent.class, (listeners) -> (MinecraftServer server) -> {
        for (FabricServerStoppedEvent listener : listeners) {
            listener.onServerStopped(server);
        }
    });

    void onServerStopped(MinecraftServer server);
}
