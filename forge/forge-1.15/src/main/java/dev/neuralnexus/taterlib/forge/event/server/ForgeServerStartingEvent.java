package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartingEvent;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link ServerStartingEvent}. */
public class ForgeServerStartingEvent extends ForgeServerEvent implements ServerStartingEvent {
    public ForgeServerStartingEvent(FMLServerStartingEvent event) {
        super(event);
    }
}
