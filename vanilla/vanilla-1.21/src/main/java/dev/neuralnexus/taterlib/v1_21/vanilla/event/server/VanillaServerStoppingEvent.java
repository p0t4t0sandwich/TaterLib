package dev.neuralnexus.taterlib.v1_21.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import net.minecraft.server.MinecraftServer;

/** Vanilla implementation of {@link ServerStoppingEvent}. */
public class VanillaServerStoppingEvent extends VanillaServerEvent implements ServerStoppingEvent {
    public VanillaServerStoppingEvent(MinecraftServer server) {
        super(server);
    }
}
