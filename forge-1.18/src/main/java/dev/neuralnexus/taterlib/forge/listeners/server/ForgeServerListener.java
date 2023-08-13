package dev.neuralnexus.taterlib.forge.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for server events.
 */
public class ForgeServerListener {
    /**
     * Called when the server starts.
     * @param event The server starting event
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        ServerListener.onServerStarting();
    }

    /**
     * Called when the server starts.
     * @param event The server started event
     */
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        ServerListener.onServerStopping();
    }

    /**
     * Called when the server stops.
     * @param event The server stopping event
     */
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        ServerListener.onServerStopping();
    }

    /**
     * Called when the server stops.
     * @param event The server stopped event
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        ServerListener.onServerStopped();
    }
}
