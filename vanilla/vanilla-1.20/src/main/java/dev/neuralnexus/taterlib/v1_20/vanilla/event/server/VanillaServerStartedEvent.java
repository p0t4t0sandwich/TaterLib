package dev.neuralnexus.taterlib.v1_20.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import net.minecraft.server.MinecraftServer;

/** Vanilla implementation of {@link ServerStartedEvent}. */
public class VanillaServerStartedEvent extends VanillaServerEvent implements ServerStartedEvent {
    public VanillaServerStartedEvent(MinecraftServer server) {
        super(server);
    }
}
