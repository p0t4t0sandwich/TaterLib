package dev.neuralnexus.taterlib.forge.abstrations.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerEvent;
import net.minecraftforge.fmlserverevents.ServerLifecycleEvent;

/**
 * Forge implementation of {@link AbstractServerEvent}.
 */
public class ForgeServerEvent implements AbstractServerEvent {
    private final ServerLifecycleEvent event;

    public ForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }
}
