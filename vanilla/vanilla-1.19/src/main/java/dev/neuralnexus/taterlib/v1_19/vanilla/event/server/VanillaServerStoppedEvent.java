package dev.neuralnexus.taterlib.v1_19.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import net.minecraft.server.MinecraftServer;

/** Vanilla implementation of {@link ServerStoppedEvent}. */
public class VanillaServerStoppedEvent extends VanillaServerEvent implements ServerStoppedEvent {
    public VanillaServerStoppedEvent(MinecraftServer server) {
        super(server);
    }
}
