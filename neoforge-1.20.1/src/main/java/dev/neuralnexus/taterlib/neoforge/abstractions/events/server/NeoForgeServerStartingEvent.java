package dev.neuralnexus.taterlib.neoforge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;

/**
 * Forge implementation of {@link AbstractServerStartingEvent}.
 */
public class NeoForgeServerStartingEvent extends NeoForgeServerEvent implements AbstractServerStartingEvent {
    public NeoForgeServerStartingEvent(ServerStartingEvent event) {
        super(event);
    }
}
