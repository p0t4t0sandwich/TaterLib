package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppedEvent;

import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

/** Forge implementation of {@link ServerStoppedEvent}. */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements ServerStoppedEvent {
    public ForgeServerStoppedEvent(FMLServerStoppedEvent event) {
        super(event);
    }
}
