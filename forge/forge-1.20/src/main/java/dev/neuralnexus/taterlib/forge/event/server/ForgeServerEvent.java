package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.forge.server.ForgeServer;
import net.minecraftforge.event.server.ServerLifecycleEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

/**
 * Forge implementation of {@link ServerEvent}.
 */
public class ForgeServerEvent implements ServerEvent {
    private final ServerLifecycleEvent event;

    public ForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server getServer() {
        return new ForgeServer(ServerLifecycleHooks.getCurrentServer());
    }
}
