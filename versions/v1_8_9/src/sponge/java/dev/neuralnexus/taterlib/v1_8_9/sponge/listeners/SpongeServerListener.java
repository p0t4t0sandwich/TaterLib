/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.listeners;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;

/** Listens to server events. */
public class SpongeServerListener {
    /**
     * Called when the server is starting.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStarting(GameStartingServerEvent event) {
        ServerEvents.STARTING.invoke(new ServerStartingEvent() {});
    }

    /**
     * Called when the server has started.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        ServerEvents.STARTED.invoke(new ServerStartedEvent() {});
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStopping(GameStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
    }

    /**
     * Called when the server has stopped.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
    }
}
