/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.event.command;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterlib.v1_13_2.forge.command.ForgeSender;

import net.minecraft.command.CommandSource;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
public class ForgeCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSource> {
    private final CommandDispatcher<CommandSource> dispatcher;

    @SuppressWarnings("unchecked")
    public ForgeCommandRegisterEvent(CommandDispatcher<?> dispatcher) {
        this.dispatcher = (CommandDispatcher<CommandSource>) dispatcher;
    }

    @Override
    public boolean isDedicated() {
        return MetaAPI.instance().side() == Side.SERVER;
    }

    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return this.dispatcher;
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        this.dispatcher.register(node);
        for (String alias : aliases) {
            this.dispatcher.register(literal(alias).redirect(node.build()));
        }
    }

    @Override
    public void registerCommand(Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSource> commandNode = wrapCommand(command);
        this.dispatcher.register(commandNode);
        for (String alias : aliases) {
            this.dispatcher.register(literal(alias).redirect(commandNode.build()));
        }
    }

    // TODO: Temporary fix until Mixins are working on 1.13.2
    private static int contextWrapper(CommandContext<CommandSource> context, Command command) {
        try {
            CommandSender source = new ForgeSender(context.getSource());
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

    // TODO: Temporary fix until Mixins are working on 1.13.2
    public static LiteralArgumentBuilder<CommandSource> wrapCommand(Command command) {
        return literal(command.name())
                .then(
                        argument("args", greedyString())
                                .executes(context -> contextWrapper(context, command)))
                .executes(context -> contextWrapper(context, command));
    }
}
