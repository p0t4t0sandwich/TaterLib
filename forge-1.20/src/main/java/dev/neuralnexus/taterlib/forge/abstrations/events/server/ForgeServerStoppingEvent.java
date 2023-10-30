package dev.neuralnexus.taterlib.forge.abstrations.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;

/**
 * Forge implementation of {@link AbstractServerStoppingEvent}.
 */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements AbstractServerStoppingEvent {
    public ForgeServerStoppingEvent(ServerStoppingEvent event) {
        super(event);
    }
}
