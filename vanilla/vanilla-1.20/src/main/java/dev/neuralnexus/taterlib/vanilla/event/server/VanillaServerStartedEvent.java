package dev.neuralnexus.taterlib.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStartedEvent}. */
public class VanillaServerStartedEvent extends VanillaServerEvent implements ServerStartedEvent {
    public VanillaServerStartedEvent(MinecraftServer server) {
        super(server);
    }
}
