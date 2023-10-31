package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

/**
 * NeoForge implementation of {@link AbstractServerStartingEvent}.
 */
public class NeoForgeServerStartingEvent extends NeoForgeServerEvent implements AbstractServerStartingEvent {
    public NeoForgeServerStartingEvent(ServerStartingEvent event) {
        super(event);
    }
}
