package dev.neuralnexus.taterlib.common.listeners.command;

import com.mojang.brigadier.Command;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

public class BrigadierHelperClass {
    public static void onRegisterBrigadierCommand(BrigadierCommandRegisterEvent event) {
        event.getDispatcher().register(literal(TaterLibCommand.getCommandName())
                .requires(source -> {
                    Sender sender = event.getSender(source);
                    return sender.hasPermission(event.isDedicated() ? (TaterLib.isHooked("luckperms") ? 0 : 4) : 0);
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
                                TaterLibCommand.executeCommand(sender, isPlayer, args);
                                return Command.SINGLE_SUCCESS;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return 0;
                            }
                        })
                )
        );
    }
}
