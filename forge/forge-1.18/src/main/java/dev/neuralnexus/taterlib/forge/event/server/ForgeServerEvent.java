package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.server.SimpleServer;

import net.minecraftforge.event.server.ServerLifecycleEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

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
