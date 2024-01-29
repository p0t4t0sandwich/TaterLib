package dev.neuralnexus.taterlib.sponge.listeners.command;

import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.sponge.adapters.SpongeAdapters;
import dev.neuralnexus.taterlib.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;

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
        // Needs to wait for the server to enter the starting state
        ServerEvents.STARTING.register(
                (startingEvent) ->
                        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                new VanillaBrigadierCommandRegisterEvent(
                                        SpongeAdapters.getCommandDispatcher(),
                                        SpongeAdapters.getCommandSelection())));

        // Sponge command registration
        CommandEvents.REGISTER_COMMAND.invoke(new SpongeCommandRegisterEvent(event));
    }
}
