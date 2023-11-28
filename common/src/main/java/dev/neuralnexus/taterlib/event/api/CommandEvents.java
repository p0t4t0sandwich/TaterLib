package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;

/** Command events. */
public class CommandEvents {
    /** Called when the command register event is fired. */
    public static final Event<CommandRegisterEvent> REGISTER_COMMAND =
            new Event<>(CommandRegisterEvent.class);

    /** Called when the brigadier command register event is fired. */
    public static final Event<BrigadierCommandRegisterEvent> REGISTER_BRIGADIER_COMMAND =
            new Event<>(BrigadierCommandRegisterEvent.class);
}
