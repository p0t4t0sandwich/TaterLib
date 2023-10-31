package dev.neuralnexus.taterlib.forge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerEvent;
import net.minecraftforge.fml.event.server.ServerLifecycleEvent;

/**
 * Forge implementation of {@link AbstractServerEvent}.
 */
public class ForgeServerEvent implements AbstractServerEvent {
    private final ServerLifecycleEvent event;

    public ForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }
}
