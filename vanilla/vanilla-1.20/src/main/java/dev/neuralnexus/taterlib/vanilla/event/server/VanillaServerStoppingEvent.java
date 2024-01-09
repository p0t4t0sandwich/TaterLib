package dev.neuralnexus.taterlib.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStoppingEvent}. */
public class VanillaServerStoppingEvent extends VanillaServerEvent implements ServerStoppingEvent {
    public VanillaServerStoppingEvent(MinecraftServer server) {
        super(server);
    }
}
