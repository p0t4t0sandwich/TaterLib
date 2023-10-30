package dev.neuralnexus.taterlib.neoforge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;

/**
 * Forge implementation of {@link AbstractServerStoppingEvent}.
 */
public class NeoForgeServerStoppingEvent extends NeoForgeServerEvent implements AbstractServerStoppingEvent {
    public NeoForgeServerStoppingEvent(ServerStoppingEvent event) {
        super(event);
    }
}
