package dev.neuralnexus.taterlib.fabric.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStartingEvent}. */
public class FabricServerStartingEvent extends FabricServerEvent implements ServerStartingEvent {
    public FabricServerStartingEvent(MinecraftServer server) {
        super(server);
    }
}
