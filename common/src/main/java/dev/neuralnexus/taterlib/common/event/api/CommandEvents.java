package dev.neuralnexus.taterlib.common.event.api;

import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;

/**
 * Command events.
 */
public class CommandEvents {
    /**
     * Called when the command register event is fired.
     */
    public static final Event<CommandRegisterEvent> REGISTER_COMMAND = new Event<>(CommandRegisterEvent.class);

    /**
     * Called when the brigadier command register event is fired.
     */
    public static final Event<BrigadierCommandRegisterEvent> REGISTER_BRIGADIER_COMMAND = new Event<>(BrigadierCommandRegisterEvent.class);
}
