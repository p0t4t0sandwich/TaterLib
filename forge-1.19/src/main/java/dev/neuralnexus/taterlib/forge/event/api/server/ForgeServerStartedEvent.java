package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartedEvent;
import net.minecraftforge.event.server.ServerStartedEvent;

/**
 * Forge implementation of {@link AbstractServerStartedEvent}.
 */
public class ForgeServerStartedEvent extends ForgeServerEvent implements AbstractServerStartedEvent {
    public ForgeServerStartedEvent(ServerStartedEvent event) {
        super(event);
    }
}
