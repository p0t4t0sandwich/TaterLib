package dev.neuralnexus.taterlib.v1_13_2.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

/** Forge implementation of {@link ServerStoppedEvent}. */
public class ForgeServerStoppedEvent extends ForgeServerEvent implements ServerStoppedEvent {
    public ForgeServerStoppedEvent(FMLServerStoppedEvent event) {
        super(event);
    }
}
