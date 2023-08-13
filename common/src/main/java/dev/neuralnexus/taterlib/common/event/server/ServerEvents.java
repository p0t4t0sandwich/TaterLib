package dev.neuralnexus.taterlib.common.event.server;

import dev.neuralnexus.taterlib.common.event.api.Event;

/**
 * Server events.
 */
public class ServerEvents {
    /**
     * Called when the server is starting.
     */
    public static final Event<ServerStartingEvent> STARTING = new Event<>(ServerStartingEvent.class);

    /**
     * Called when the server has started.
     */
    public static final Event<ServerStartedEvent> STARTED = new Event<>(ServerStartedEvent.class);

    /**
     * Called when the server is stopping.
     */
    public static final Event<ServerStoppingEvent> STOPPING = new Event<>(ServerStoppingEvent.class);

    /**
     * Called when the server has stopped.
     */
    public static final Event<ServerStoppedEvent> STOPPED = new Event<>(ServerStoppedEvent.class);

    @FunctionalInterface
    public interface ServerStartingEvent {
        void onServerStarting();
    }

    @FunctionalInterface
    public interface ServerStartedEvent {
        void onServerStarted();
    }

    @FunctionalInterface
    public interface ServerStoppingEvent {
        void onServerStopping();
    }

    @FunctionalInterface
    public interface ServerStoppedEvent {
        void onServerStopped();
    }
}
