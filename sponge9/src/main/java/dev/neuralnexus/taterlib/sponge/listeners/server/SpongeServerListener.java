package dev.neuralnexus.taterlib.sponge.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppedGameEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;

/**
 * Listens to server events.
 */
public class SpongeServerListener {
    /**
     * Called when the server is starting.
     * @param event The event.
     */
    @Listener
    public void onServerStarting(StartingEngineEvent<Server> event) {
        ServerListener.onServerStarting();
    }

    /**
     * Called when the server has started.
     * @param event The event.
     */
    @Listener
    public void onServerStarted(StartedEngineEvent<Server> event) {
        ServerListener.onServerStarted();
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        ServerListener.onServerStopping();
    }

    /**
     * Called when the server has stopped.
     * @param event The event.
     */
    @Listener
    public void onServerStopped(StoppedGameEvent event) {
        ServerListener.onServerStopped();
    }
}
