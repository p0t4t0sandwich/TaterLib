package dev.neuralnexus.taterlib.neoforge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;

/**
 * Forge implementation of {@link AbstractServerStoppedEvent}.
 */
public class NeoForgeServerStoppedEvent extends NeoForgeServerEvent implements AbstractServerStoppedEvent {
    public NeoForgeServerStoppedEvent(ServerStoppedEvent event) {
        super(event);
    }
}
