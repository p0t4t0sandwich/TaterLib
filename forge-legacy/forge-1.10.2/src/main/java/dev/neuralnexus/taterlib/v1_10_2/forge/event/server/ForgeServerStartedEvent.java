package dev.neuralnexus.taterlib.v1_10_2.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/** Forge implementation of {@link ServerStartedEvent}. */
public class ForgeServerStartedEvent extends ForgeServerEvent implements ServerStartedEvent {
    public ForgeServerStartedEvent(FMLServerStartedEvent event) {
        super(event);
    }
}
