package dev.neuralnexus.taterlib.command;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;

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
        boolean isPlayer = event.isPlayer(source);
        if (isPlayer) {
            commandSender = event.getPlayer(source);
        }
        return command.execute(commandSender, command.getName(), args) ? 1 : 0;
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
        return literal(command.getName())
                .then(
                        argument("args", greedyString())
                                .executes(context -> contextWrapper(event, context, command)))
                .executes(context -> contextWrapper(event, context, command));
    }
}
