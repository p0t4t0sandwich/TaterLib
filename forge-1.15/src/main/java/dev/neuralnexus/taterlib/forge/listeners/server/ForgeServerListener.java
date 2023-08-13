package dev.neuralnexus.taterlib.forge.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

/**
 * Listens for server events.
 */
public class ForgeServerListener {
    /**
     * Called when the server starts.
     * @param event The server starting event
     */
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        ServerListener.onServerStarting();
    }

    /**
     * Called when the server starts.
     * @param event The server started event
     */
    @SubscribeEvent
    public void onServerStarted(FMLServerStartedEvent event) {
        ServerListener.onServerStopping();
    }

    /**
     * Called when the server stops.
     * @param event The server stopping event
     */
    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerListener.onServerStopping();
    }

    /**
     * Called when the server stops.
     * @param event The server stopped event
     */
    @SubscribeEvent
    public void onServerStopped(FMLServerStoppedEvent event) {
        ServerListener.onServerStopped();
    }
}
