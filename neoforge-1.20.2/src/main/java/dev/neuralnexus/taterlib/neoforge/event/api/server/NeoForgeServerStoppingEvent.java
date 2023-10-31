package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

/**
 * NeoForge implementation of {@link AbstractServerStoppingEvent}.
 */
public class NeoForgeServerStoppingEvent extends NeoForgeServerEvent implements AbstractServerStoppingEvent {
    public NeoForgeServerStoppingEvent(ServerStoppingEvent event) {
        super(event);
    }
}
