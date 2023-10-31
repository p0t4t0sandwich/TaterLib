package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;

/**
 * Forge implementation of {@link AbstractServerStartingEvent}.
 */
public class ForgeServerStartingEvent extends ForgeServerEvent implements AbstractServerStartingEvent {
    public ForgeServerStartingEvent(ServerStartingEvent event) {
        super(event);
    }
}
