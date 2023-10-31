package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppingEvent;

/**
 * Forge implementation of {@link ServerStoppingEvent}.
 */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements ServerStoppingEvent {
    public ForgeServerStoppingEvent(net.minecraftforge.event.server.ServerStoppingEvent event) {
        super(event);
    }
}
