package dev.neuralnexus.taterlib.velocity.listeners.server;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import dev.neuralnexus.taterlib.velocity.abstractions.events.server.VelocityServerStartingEvent;
import dev.neuralnexus.taterlib.velocity.abstractions.events.server.VelocityServerStoppingEvent;

public class VelocityServerListener {
    /**
     * Called when the server has started.
     * @param event The event.
     */
    @Subscribe
    public void onServerStarting(ProxyInitializeEvent event) {
        ServerListener.onServerStarting(new VelocityServerStartingEvent(event));
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Subscribe
    public void onServerStopping(ProxyShutdownEvent event) {
        ServerListener.onServerStopping(new VelocityServerStoppingEvent(event));
    }
}
