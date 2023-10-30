package dev.neuralnexus.taterlib.neoforge.listeners.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvents;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.server.NeoForgeServerStartedEvent;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.server.NeoForgeServerStartingEvent;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.server.NeoForgeServerStoppedEvent;
import dev.neuralnexus.taterlib.neoforge.abstractions.events.server.NeoForgeServerStoppingEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for server events.
 */
public class NeoForgeServerListener {
    /**
     * Called when the server starts.
     * @param event The server starting event
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        ServerEvents.STARTING.invoke(new NeoForgeServerStartingEvent(event));
    }

    /**
     * Called when the server starts.
     * @param event The server started event
     */
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        ServerEvents.STARTED.invoke(new NeoForgeServerStartedEvent(event));
    }

    /**
     * Called when the server stops.
     * @param event The server stopping event
     */
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new NeoForgeServerStoppingEvent(event));
    }

    /**
     * Called when the server stops.
     * @param event The server stopped event
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(new NeoForgeServerStoppedEvent(event));
    }
}
