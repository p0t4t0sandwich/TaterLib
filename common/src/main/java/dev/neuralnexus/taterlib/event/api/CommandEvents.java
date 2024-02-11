package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Command events. */
public class CommandEvents {
    /** Called when the command register event is fired. */
    public static final EventManager<CommandRegisterEvent> REGISTER_COMMAND =
            new EventManager<>(CommandRegisterEvent.class);

    /** Called when the brigadier command register event is fired. */
    public static final EventManager<BrigadierCommandRegisterEvent> REGISTER_BRIGADIER_COMMAND =
            new EventManager<>(BrigadierCommandRegisterEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> getEvents() {
        return new HashSet<>(Arrays.asList(REGISTER_COMMAND, REGISTER_BRIGADIER_COMMAND));
    }
}
