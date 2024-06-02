package dev.neuralnexus.taterlib.v1_19.forge.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.server.VanillaServerStartedEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.server.VanillaServerStartingEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.server.VanillaServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.server.VanillaServerStoppingEvent;

import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Listens for server events. */
public class ForgeServerListener {
    /**
     * Called when the server starts.
     *
     * @param event The server starting event
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        ServerEvents.STARTING.invoke(new VanillaServerStartingEvent(event.getServer()));
    }

    /**
     * Called when the server starts.
     *
     * @param event The server started event
     */
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        ServerEvents.STARTED.invoke(new VanillaServerStartedEvent(event.getServer()));
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopping event
     */
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new VanillaServerStoppingEvent(event.getServer()));
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopped event
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent(event.getServer()));
    }
}
