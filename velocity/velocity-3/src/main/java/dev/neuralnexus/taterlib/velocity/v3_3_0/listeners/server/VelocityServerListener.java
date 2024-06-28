/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.server;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.server.VelocityServerStartingEvent;

/** Listens for server events. */
public class VelocityServerListener {
    /**
     * Called when the server has started.
     *
     * @param event The event.
     */
    @Subscribe
    public void onServerStarting(ProxyInitializeEvent event) {
        ServerEvents.STARTING.invoke(new VelocityServerStartingEvent(event));
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @Subscribe
    public void onServerStopping(ProxyShutdownEvent event) {
        //        ServerEvents.STOPPING.invoke(new VelocityServerStoppingEvent(event));
    }
}
