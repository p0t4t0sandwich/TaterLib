package dev.neuralnexus.taterlib.v1_7_10.fabric.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStartedEvent}. */
public class FabricServerStartedEvent extends FabricServerEvent implements ServerStartedEvent {
    public FabricServerStartedEvent(MinecraftServer server) {
        super(server);
    }
}
