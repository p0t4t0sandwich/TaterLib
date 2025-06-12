/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSource;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.command.CommandCause;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;

/** Wraps a command callback into a Sponge Command. */
@ToBeLibrary("brigadier-general")
public class SpongeCommandWrapper implements CommandExecutor {
    private final Command.Callback callback;
    private final String commandName;

    public SpongeCommandWrapper(Command.Callback callback, String commandName) {
        this.callback = callback;
        this.commandName = commandName;
    }

    @Override
    @SuppressWarnings("RedundantThrows")
    public CommandResult execute(CommandContext context) throws CommandException {
        try {
            String[] args = context.requireOne(Parameter.string().key("args").build()).split(" ");
            CommandCause sender = context.cause();
            callback.execute((CommandSource) sender, commandName, args);
        } catch (Exception e) {
            TaterAPI.logger().error("An exception occurred while executing a command", e);
            return CommandResult.builder().result(0).error(Component.text(e.getMessage())).build();
        }
        return CommandResult.builder().result(1).build();
    }
}
