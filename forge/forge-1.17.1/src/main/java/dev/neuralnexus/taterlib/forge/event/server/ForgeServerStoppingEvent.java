package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import net.minecraftforge.fmlserverevents.FMLServerStoppingEvent;

/** Forge implementation of {@link ServerStoppingEvent}. */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements ServerStoppingEvent {
    public ForgeServerStoppingEvent(FMLServerStoppingEvent event) {
        super(event);
    }
}
