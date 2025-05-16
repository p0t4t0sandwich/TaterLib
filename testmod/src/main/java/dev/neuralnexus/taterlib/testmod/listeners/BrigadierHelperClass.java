/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.listeners;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;

/** Helper class for brigadier commands (prevents ClassNotFound exceptions). */
public class BrigadierHelperClass {
    @SuppressWarnings("unchecked")
    public static void onRegisterBrigadierCommand(
            BrigadierCommandRegisterEvent event,
            Command command,
            String commandName,
            String... aliases) {

        // Create your command
        LiteralArgumentBuilder<CommandSender> brigCommand = literal(command.name());

        // Add permission check and any other required logic (e.g. player check or dimension check)
        brigCommand.requires(
                source ->
                        source.hasPermission(
                                // Checks if not dedicated, or if LuckPerms is installed
                                // then sets the permission level to 0, otherwise 4
                                event.isDedicated()
                                        ? (TaterAPI.isHooked("luckperms") ? 0 : 4)
                                        : 0));

        // Create an argument for the command, for this example we will use
        // a greedy string that we can split into an array.
        RequiredArgumentBuilder<CommandSender, String> requiredArgument =
                argument("args", greedyString());
        requiredArgument.executes(
                context -> {
                    try {
                        CommandSender source = context.getSource();

                        String[] args = context.getArgument("args", String.class).split(" ");
                        return command.execute(source, command.name(), args) ? 1 : 0;
                    } catch (Exception e) {
                        TaterAPI.logger().error("Error executing command: " + command.name(), e);
                        throw e;
                    }
                });

        // Add the argument to the command
        brigCommand.then(requiredArgument);

        // Register the command
        event.registerCommand(brigCommand, commandName, aliases);
    }
}
