package dev.neuralnexus.taterlib.v1_18_2.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

/** Forge implementation of {@link ServerStoppingEvent}. */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements ServerStoppingEvent {
    public ForgeServerStoppingEvent(net.minecraftforge.event.server.ServerStoppingEvent event) {
        super(event);
    }
}
