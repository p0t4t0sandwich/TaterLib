package dev.neuralnexus.taterlib.neoforge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

/**
 * NeoForge implementation of {@link AbstractServerStoppingEvent}.
 */
public class NeoForgeServerStoppingEvent extends NeoForgeServerEvent implements AbstractServerStoppingEvent {
    public NeoForgeServerStoppingEvent(ServerStoppingEvent event) {
        super(event);
    }
}
