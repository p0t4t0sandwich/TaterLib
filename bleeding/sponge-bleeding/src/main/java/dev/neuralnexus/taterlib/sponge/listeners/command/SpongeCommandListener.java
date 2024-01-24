package dev.neuralnexus.taterlib.sponge.listeners.command;

import com.mojang.brigadier.tree.LiteralCommandNode;

import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.sponge.event.command.SpongeBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.event.command.SpongeCommandRegisterEvent;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.common.command.manager.SpongeCommandManager;

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
    @Listener
    public void onRegisterBrigadierCommands(
            final RegisterCommandEvent<LiteralCommandNode<?>> event) {
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new SpongeBrigadierCommandRegisterEvent(
                        (SpongeCommandManager) Sponge.server().commandManager()));
    }
}
