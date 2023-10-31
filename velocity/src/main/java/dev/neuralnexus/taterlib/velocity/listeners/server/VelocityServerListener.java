package dev.neuralnexus.taterlib.velocity.listeners.server;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.velocity.event.api.server.VelocityServerStartingEvent;
import dev.neuralnexus.taterlib.velocity.event.api.server.VelocityServerStoppingEvent;

/**
 * Listens for server events.
 */
public class VelocityServerListener {
    /**
     * Called when the server has started.
     * @param event The event.
     */
    @Subscribe
    public void onServerStarting(ProxyInitializeEvent event) {
        ServerEvents.STARTING.invoke(new VelocityServerStartingEvent(event));
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Subscribe
    public void onServerStopping(ProxyShutdownEvent event) {
        ServerEvents.STOPPING.invoke(new VelocityServerStoppingEvent(event));
    }
}
