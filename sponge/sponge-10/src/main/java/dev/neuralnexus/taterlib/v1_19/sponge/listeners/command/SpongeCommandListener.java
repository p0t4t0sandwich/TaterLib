/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_19.sponge.listeners.command;

import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterlib.v1_19.sponge.event.command.SpongeCommandRegisterEvent;

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

    /**
     * Register brigadier commands.
     *
     * @param event The event
     */
    //    @Listener
    //    public void onRegisterBrigadierCommands(
    //            final RegisterCommandEvent<LiteralCommandNode<?>> event) {
    //        event.register()
    //    }
}
