package dev.neuralnexus.taterlib.v1_14_4.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link ServerStartingEvent}. */
public class ForgeServerStartingEvent extends ForgeServerEvent implements ServerStartingEvent {
    public ForgeServerStartingEvent(FMLServerStartingEvent event) {
        super(event);
    }
}
