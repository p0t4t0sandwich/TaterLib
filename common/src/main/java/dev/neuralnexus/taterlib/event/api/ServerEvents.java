package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;
import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;
import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

/** Server events. */
public class ServerEvents {
    /** Called when the server is starting. */
    public static final EventHolder<ServerStartingEvent> STARTING =
            new EventHolder<>(ServerStartingEvent.class);

    /** Called when the server has started. */
    public static final EventHolder<ServerStartedEvent> STARTED =
            new EventHolder<>(ServerStartedEvent.class);

    /** Called when the server is stopping. */
    public static final EventHolder<ServerStoppingEvent> STOPPING =
            new EventHolder<>(ServerStoppingEvent.class);

    /** Called when the server has stopped. */
    public static final EventHolder<ServerStoppedEvent> STOPPED =
            new EventHolder<>(ServerStoppedEvent.class);
}
