package dev.neuralnexus.taterlib.v1_18.fabric.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStoppingEvent}. */
public class FabricServerStoppingEvent extends FabricServerEvent implements ServerStoppingEvent {
    public FabricServerStoppingEvent(MinecraftServer server) {
        super(server);
    }
}
