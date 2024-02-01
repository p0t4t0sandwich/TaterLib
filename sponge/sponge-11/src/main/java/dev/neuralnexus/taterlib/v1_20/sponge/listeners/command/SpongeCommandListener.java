package dev.neuralnexus.taterlib.v1_20.sponge.listeners.command;

import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.v1_20.sponge.event.command.SpongeCommandRegisterEvent;

import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;

/** Listens to command events. */
public class SpongeCommandListener {
    /**
     * Register commands.
     *
     * @param event The event
     */
    @Listener
    public void onRegisterCommands(final RegisterCommandEvent<Command.Parameterized> event) {
        CommandEvents.REGISTER_COMMAND.invoke(new SpongeCommandRegisterEvent(event));
    }
}
