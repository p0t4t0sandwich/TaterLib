package dev.neuralnexus.taterlib.v1_17_1.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.v1_17_1.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.server.SimpleServer;

import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;
import net.minecraftforge.fmlserverevents.ServerLifecycleEvent;

/** Forge implementation of {@link ServerEvent}. */
public class ForgeServerEvent implements ServerEvent {
    private final ServerLifecycleEvent event;

    public ForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new ForgeServer(ServerLifecycleHooks.getCurrentServer());
    }
}