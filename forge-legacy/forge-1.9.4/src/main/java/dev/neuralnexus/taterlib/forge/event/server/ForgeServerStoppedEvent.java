package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

/** Forge implementation of {@link ServerStoppedEvent}. */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements ServerStoppedEvent {
    public ForgeServerStoppedEvent(FMLServerStoppedEvent event) {
        super(event);
    }
}
