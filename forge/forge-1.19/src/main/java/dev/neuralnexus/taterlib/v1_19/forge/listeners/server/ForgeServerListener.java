/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_19.forge.listeners.server;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartingEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppingEventImpl;

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
        ServerEvents.STARTING.invoke(new ServerStartingEventImpl());
    }

    /**
     * Called when the server starts.
     *
     * @param event The server started event
     */
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        ServerEvents.STARTED.invoke(new ServerStartedEventImpl());
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopping event
     */
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new ServerStoppingEventImpl());
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopped event
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(new ServerStoppedEventImpl());
    }
}
