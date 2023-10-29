package dev.neuralnexus.taterlib.neoforge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
import net.minecraftforge.event.server.ServerStartedEvent;

/**
 * Forge implementation of {@link AbstractServerStartedEvent}.
 */
public class NeoForgeServerStartedEvent extends NeoForgeServerEvent implements AbstractServerStartedEvent {
    public NeoForgeServerStartedEvent(ServerStartedEvent event) {
        super(event);
    }
}
