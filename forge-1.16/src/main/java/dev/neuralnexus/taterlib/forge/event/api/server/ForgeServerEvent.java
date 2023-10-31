package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import net.minecraftforge.fml.event.server.ServerLifecycleEvent;

/**
 * Forge implementation of {@link ServerEvent}.
 */
public class ForgeServerEvent implements ServerEvent {
    private final ServerLifecycleEvent event;

    public ForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }
}
