package dev.neuralnexus.taterlib.common.listeners.command;

import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

/**
 * Helper class for brigadier commands (prevents ClassNotFound exceptions).
 */
public class BrigadierHelperClass {
    public static void onRegisterBrigadierCommand(BrigadierCommandRegisterEvent event) {
        Command command = new TaterLibCommand();
        event.registerCommand(literal(command.getName())
                .requires(source -> {
                    Sender sender = event.getSender(source);
                    return sender.hasPermission(event.isDedicated() ? (TaterAPIProvider.get().isHooked("luckperms") ? 0 : 4) : 0);
                })
                .then(argument("args", greedyString())
                        .executes(context -> {
                            try {
                                Object source = context.getSource();
                                Sender sender = event.getSender(source);

                                String[] args = context.getArgument("args", String.class).split(" ");
                                boolean isPlayer = event.isPlayer(source);
                                if (isPlayer) {
                                    sender = event.getPlayer(source);
                                }
                                return command.execute(sender, command.getName(), args) ? 1 : 0;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return 0;
                            }
                        })
                ).build()
        );
    }
}
