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
    public static final Event<AbstractServerStartingEvent> STARTING = new Event<>(AbstractServerStartingEvent.class);

    /**
     * Called when the server has started.
     */
    public static final Event<AbstractServerStartedEvent> STARTED = new Event<>(AbstractServerStartedEvent.class);

    /**
     * Called when the server is stopping.
     */
    public static final Event<AbstractServerStoppingEvent> STOPPING = new Event<>(AbstractServerStoppingEvent.class);

    /**
     * Called when the server has stopped.
     */
    public static final Event<AbstractServerStoppedEvent> STOPPED = new Event<>(AbstractServerStoppedEvent.class);
}
