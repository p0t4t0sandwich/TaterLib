package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

/**
 * NeoForge implementation of {@link AbstractServerStartedEvent}.
 */
public class NeoForgeServerStartedEvent extends NeoForgeServerEvent implements AbstractServerStartedEvent {
    public NeoForgeServerStartedEvent(ServerStartedEvent event) {
        super(event);
    }
}
