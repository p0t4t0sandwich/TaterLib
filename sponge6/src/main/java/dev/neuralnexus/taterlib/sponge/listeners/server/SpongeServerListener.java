package dev.neuralnexus.taterlib.sponge.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;

/**
 * Listens to server events.
 */
public class SpongeServerListener {
    /**
     * Called when the server is starting.
     * @param event The event.
     */
    @Listener
    public void onServerStarting(GameStartingServerEvent event) {
        ServerListener.onServerStarting();
    }

    /**
     * Called when the server has started.
     * @param event The event.
     */
    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        ServerListener.onServerStarted();
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Listener
    public void onServerStopping(GameStoppedServerEvent event) {
        ServerListener.onServerStopping();
    }

    /**
     * Called when the server has stopped.
     * @param event The event.
     */
    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        ServerListener.onServerStopped();
    }
}
