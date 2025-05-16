/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;

import static dev.neuralnexus.taterapi.command.Commands.argument;
import static dev.neuralnexus.taterapi.command.Commands.literal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;

/** Simple wrapper for brigadier commands */
@ToBeLibrary("brigadier-general")
public class SimpleBrigadierWrapper {
    private static int contextWrapper(CommandContext<CommandSender> context, Command command) {
        try {
            CommandSender source = context.getSource();
            String[] args = new String[] {};
            try {
                args = context.getArgument("args", String.class).split(" ");
            } catch (IllegalArgumentException ignored) {
            }
            return command.execute(source, command.name(), args) ? 1 : 0;
        } catch (Exception e) {
            TaterAPI.logger().error("Error executing command: " + command.name(), e);
            throw e;
        }
    }

    /**
     * Registers a command wrapped in a simple brigadier command
     *
     * @param command The command
     */
    public static LiteralArgumentBuilder<CommandSender> wrapCommand(Command command) {
        return literal(command.name())
                .then(
                        argument("args", greedyString())
                                .executes(context -> contextWrapper(context, command)))
                .executes(context -> contextWrapper(context, command));
    }
}
