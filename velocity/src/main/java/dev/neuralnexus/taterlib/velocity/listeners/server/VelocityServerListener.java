package dev.neuralnexus.taterlib.velocity.listeners.server;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;

public class VelocityServerListener {
    /**
     * Called when the server has started.
     * @param event The event.
     */
    @Subscribe
    public void onServerStarting(ProxyInitializeEvent event) {
        // Run server started event
        ServerListener.onServerStarting();
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Subscribe
    public void onServerStopping(ProxyShutdownEvent event) {
        // Run server stopped event
        ServerListener.onServerStopping();
    }
}
