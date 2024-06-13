package dev.neuralnexus.taterlib.v1_21.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

import net.minecraft.server.MinecraftServer;

/** Vanilla implementation of {@link ServerStartingEvent}. */
public class VanillaServerStartingEvent extends VanillaServerEvent implements ServerStartingEvent {
    public VanillaServerStartingEvent(MinecraftServer server) {
        super(server);
    }
}
