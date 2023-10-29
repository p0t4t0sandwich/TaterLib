package dev.neuralnexus.taterlib.forge.abstrations.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

/**
 * Forge implementation of {@link AbstractServerStartedEvent}.
 */
public class ForgeServerStartedEvent extends ForgeServerEvent implements AbstractServerStartedEvent {
    public ForgeServerStartedEvent(FMLServerStartedEvent event) {
        super(event);
    }
}
