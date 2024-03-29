package dev.neuralnexus.taterlib.forge.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStartedEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStartingEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.forge.event.server.ForgeServerStoppingEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

/** Listens for server events. */
public class ForgeServerListener {
    /**
     * Called when the server starts.
     *
     * @param event The server starting event
     */
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        ServerEvents.STARTING.invoke(new ForgeServerStartingEvent(event));
    }

    /**
     * Called when the server starts.
     *
     * @param event The server started event
     */
    @SubscribeEvent
    public void onServerStarted(FMLServerStartedEvent event) {
        ServerEvents.STARTED.invoke(new ForgeServerStartedEvent(event));
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopping event
     */
    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new ForgeServerStoppingEvent(event));
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopped event
     */
    @SubscribeEvent
    public void onServerStopped(FMLServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(new ForgeServerStoppedEvent(event));
    }
}
