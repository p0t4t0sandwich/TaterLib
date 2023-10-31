package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/**
 * NeoForge implementation of {@link AbstractServerStoppedEvent}.
 */
public class NeoForgeServerStoppedEvent extends NeoForgeServerEvent implements AbstractServerStoppedEvent {
    public NeoForgeServerStoppedEvent(ServerStoppedEvent event) {
        super(event);
    }
}
