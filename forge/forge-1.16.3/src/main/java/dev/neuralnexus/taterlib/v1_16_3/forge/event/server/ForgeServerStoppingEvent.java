package dev.neuralnexus.taterlib.v1_16_3.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

/** Forge implementation of {@link ServerStoppingEvent}. */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements ServerStoppingEvent {
    public ForgeServerStoppingEvent(FMLServerStoppingEvent event) {
        super(event);
    }
}
