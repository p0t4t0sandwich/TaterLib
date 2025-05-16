/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.event.command;

import static dev.neuralnexus.taterapi.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Side;

/** Vanilla implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
public class VanillaCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSource> {
    private final CommandDispatcher<CommandSource> dispatcher;

    @SuppressWarnings("unchecked")
    public VanillaCommandRegisterEvent(CommandDispatcher<?> dispatcher) {
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
        final LiteralArgumentBuilder<CommandSource> literalArgumentBuilder =
                SimpleBrigadierWrapper.wrapCommand(command);
        this.dispatcher.register(literalArgumentBuilder);
        for (String alias : aliases) {
            this.dispatcher.register(literal(alias).redirect(literalArgumentBuilder.build()));
        }
    }
}
