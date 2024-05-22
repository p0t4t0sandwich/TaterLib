package dev.neuralnexus.taterlib.v1_14.fabric.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStoppedEvent}. */
public class FabricServerStoppedEvent extends FabricServerEvent implements ServerStoppedEvent {
    public FabricServerStoppedEvent(MinecraftServer server) {
        super(server);
    }
}
