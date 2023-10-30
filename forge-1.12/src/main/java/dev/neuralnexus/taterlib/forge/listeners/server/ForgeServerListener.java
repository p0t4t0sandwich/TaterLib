package dev.neuralnexus.taterlib.forge.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import dev.neuralnexus.taterlib.forge.abstrations.events.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.forge.abstrations.events.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.forge.abstrations.events.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.forge.abstrations.events.server.ForgeServerStoppingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

/**
 * Listens for server events.
 */
public class ForgeServerListener {
    /**
     * Called when the server starts.
     * @param event The server starting event
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        ServerListener.onServerStarting(new ForgeServerStartingEvent(event));
    }

    /**
     * Called when the server starts.
     * @param event The server started event
     */
    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        ServerListener.onServerStarted(new ForgeServerStartedEvent(event));
    }

    /**
     * Called when the server stops.
     * @param event The server stopping event
     */
    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerListener.onServerStopping(new ForgeServerStoppingEvent(event));
    }

    /**
     * Called when the server stops.
     * @param event The server stopped event
     */
    @Mod.EventHandler
    public void onServerStopped(FMLServerStoppedEvent event) {
        ServerListener.onServerStopped(new ForgeServerStoppedEvent(event));
    }
}
