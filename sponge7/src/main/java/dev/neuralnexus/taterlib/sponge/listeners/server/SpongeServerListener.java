package dev.neuralnexus.taterlib.sponge.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import dev.neuralnexus.taterlib.sponge.abstractions.events.server.SpongeServerStartedEvent;
import dev.neuralnexus.taterlib.sponge.abstractions.events.server.SpongeServerStartingEvent;
import dev.neuralnexus.taterlib.sponge.abstractions.events.server.SpongeServerStoppedEvent;
import dev.neuralnexus.taterlib.sponge.abstractions.events.server.SpongeServerStoppingEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;

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
        ServerListener.onServerStarting(new SpongeServerStartingEvent(event));
    }

    /**
     * Called when the server has started.
     * @param event The event.
     */
    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        ServerListener.onServerStarted(new SpongeServerStartedEvent(event));
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @Listener
    public void onServerStopping(GameStoppingEvent event) {
        ServerListener.onServerStopping(new SpongeServerStoppingEvent(event));
    }

    /**
     * Called when the server has stopped.
     * @param event The event.
     */
    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        ServerListener.onServerStopped(new SpongeServerStoppedEvent(event));
    }
}
