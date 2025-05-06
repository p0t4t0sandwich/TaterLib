/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;

/** Simple wrapper for brigadier commands */
public class SimpleBrigadierWrapper {
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static int contextWrapper(
            BrigadierCommandRegisterEvent event, CommandContext context, Command command) {
        Object source = context.getSource();
        CommandSender commandSender = event.getSender(source);
        String[] args = new String[] {};
        try {
            args = ((String) context.getArgument("args", String.class)).split(" ");
        } catch (IllegalArgumentException ignored) {
        }
        // TODO: Resolve this before merging command rework
        // if (commandSender.isPlayer()) {
        //     commandSender = commandSender.getPlayer();
        // }
        return command.execute(commandSender, command.name(), args) ? 1 : 0;
    }

    /**
     * Registers a command wrapped in a simple brigadier command
     *
     * @param event The event
     * @param command The command
     */
    @SuppressWarnings("rawtypes")
    public static LiteralArgumentBuilder wrapCommand(
            BrigadierCommandRegisterEvent event, Command command) {
        return literal(command.name())
                .then(
                        argument("args", greedyString())
                                .executes(context -> contextWrapper(event, context, command)))
                .executes(context -> contextWrapper(event, context, command));
    }
}
