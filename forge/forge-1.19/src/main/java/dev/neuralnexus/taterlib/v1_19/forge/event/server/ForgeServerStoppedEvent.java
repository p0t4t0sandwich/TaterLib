package dev.neuralnexus.taterlib.v1_19.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

/** Forge implementation of {@link ServerStoppedEvent}. */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements ServerStoppedEvent {
    public ForgeServerStoppedEvent(net.minecraftforge.event.server.ServerStoppedEvent event) {
        super(event);
    }
}
