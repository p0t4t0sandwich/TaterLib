package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/**
 * Forge implementation of {@link ServerStartedEvent}.
 */
public class ForgeServerStartedEvent extends ForgeServerEvent implements ServerStartedEvent {
    public ForgeServerStartedEvent(FMLServerStartedEvent event) {
        super(event);
    }
}
