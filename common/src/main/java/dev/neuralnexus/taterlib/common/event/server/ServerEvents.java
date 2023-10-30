package dev.neuralnexus.taterlib.common.event.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartingEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppedEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import dev.neuralnexus.taterlib.common.event.api.Event;

/**
 * Server events.
 */
public class ServerEvents {
    /**
     * Called when the server is starting.
     */
    public static final Event<ServerStartingEvent, AbstractServerStartingEvent> STARTING = new Event<>(ServerStartingEvent.class);

    /**
     * Called when the server has started.
     */
    public static final Event<ServerStartedEvent, AbstractServerStartedEvent> STARTED = new Event<>(ServerStartedEvent.class);

    /**
     * Called when the server is stopping.
     */
    public static final Event<ServerStoppingEvent, AbstractServerStoppingEvent> STOPPING = new Event<>(ServerStoppingEvent.class);

    /**
     * Called when the server has stopped.
     */
    public static final Event<ServerStoppedEvent, AbstractServerStoppedEvent> STOPPED = new Event<>(ServerStoppedEvent.class);

    @FunctionalInterface
    public interface ServerStartingEvent {
        void onServerStarting(AbstractServerStartingEvent event);
    }

    @FunctionalInterface
    public interface ServerStartedEvent {
        void onServerStarted(AbstractServerStartedEvent event);
    }

    @FunctionalInterface
    public interface ServerStoppingEvent {
        void onServerStopping(AbstractServerStoppingEvent event);
    }

    @FunctionalInterface
    public interface ServerStoppedEvent {
        void onServerStopped(AbstractServerStoppedEvent event);
    }
}
