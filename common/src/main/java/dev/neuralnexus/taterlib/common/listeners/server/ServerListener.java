package dev.neuralnexus.taterlib.common.listeners.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartingEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppedEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import dev.neuralnexus.taterlib.common.event.server.ServerEvents;

public class ServerListener {
    /**
     * Called when the server is starting.
     * @param event The event.
     */
    public static void onServerStarting(AbstractServerStartingEvent event) {
        ServerEvents.STARTING.invoke(event);
    }

    /**
     * Called when the server has started.
     * @param event The event.
     */
    public static void onServerStarted(AbstractServerStartedEvent event) {
        ServerEvents.STARTED.invoke(event);
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    public static void onServerStopping(AbstractServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(event);
    }

    /**
     * Called when the server stops.
     * @param event The event.
     */
    public static void onServerStopped(AbstractServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(event);
    }
}
