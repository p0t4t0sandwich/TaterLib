package dev.neuralnexus.taterlib.common.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

/**
 * Simple wrapper for brigadier commands
 */
public class SimpleBrigadierWrapper {
    /**
     * Registers a command wrapped in a simple brigadier command
     * @param event The event
     * @param command The command
     */
    public static LiteralCommandNode wrapCommand(BrigadierCommandRegisterEvent event, Command command) {
        return literal(command.getName())
                .then(argument("args", greedyString()))
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
                }).build();
    }
}
