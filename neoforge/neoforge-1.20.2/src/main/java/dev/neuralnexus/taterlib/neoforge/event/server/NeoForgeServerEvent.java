package dev.neuralnexus.taterlib.neoforge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.neoforge.server.NeoForgeServer;

import net.neoforged.neoforge.event.server.ServerLifecycleEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/** NeoForge implementation of {@link ServerEvent}. */
public class NeoForgeServerEvent implements ServerEvent {
    private final ServerLifecycleEvent event;

    public NeoForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new NeoForgeServer(ServerLifecycleHooks.getCurrentServer());
    }
}
