package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;
import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;
import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

/** Server events. */
public class ServerEvents {
    /** Called when the server is starting. */
    public static final Event<ServerStartingEvent> STARTING =
            new Event<>(ServerStartingEvent.class);

    /** Called when the server has started. */
    public static final Event<ServerStartedEvent> STARTED = new Event<>(ServerStartedEvent.class);

    /** Called when the server is stopping. */
    public static final Event<ServerStoppingEvent> STOPPING =
            new Event<>(ServerStoppingEvent.class);

    /** Called when the server has stopped. */
    public static final Event<ServerStoppedEvent> STOPPED = new Event<>(ServerStoppedEvent.class);
}
