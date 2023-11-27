package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

/**
 * Forge implementation of {@link ServerStartedEvent}.
 */
public class ForgeServerStartedEvent extends ForgeServerEvent implements ServerStartedEvent {
    public ForgeServerStartedEvent(FMLServerStartedEvent event) {
        super(event);
    }
}
