/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.command.impl;

import static dev.neuralnexus.taterapi.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Side;

/**
 * General implementation of {@link CommandRegisterEvent} and {@link BrigadierCommandRegisterEvent}.
 */
public record CommandRegisterEventImpl(CommandDispatcher<CommandSource> dispatcher)
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSource> {
    @Override
    public boolean isDedicated() {
        return MetaAPI.instance().side() == Side.SERVER;
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
