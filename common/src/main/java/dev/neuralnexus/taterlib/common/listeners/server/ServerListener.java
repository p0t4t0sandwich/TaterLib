package dev.neuralnexus.taterlib.common.listeners.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvents;

public class ServerListener {
    /**
     * Called when the server is starting.
     */
    public static void onServerStarting() {
        // Fire cross-API event
        ServerEvents.STARTING.invoke(new Object[]{});
    }

    /**
     * Called when the server has started.
     */
    public static void onServerStarted() {
        // Fire cross-API event
        ServerEvents.STARTED.invoke(new Object[]{});
    }

    /**
     * Called when the server is stopping.
     */
    public static void onServerStopping() {
        // Fire cross-API event
        ServerEvents.STOPPING.invoke(new Object[]{});
    }

    /**
     * Called when the server stops.
     */
    public static void onServerStopped() {
        // Fire cross-API event
        ServerEvents.STOPPED.invoke(new Object[]{});
    }
}
