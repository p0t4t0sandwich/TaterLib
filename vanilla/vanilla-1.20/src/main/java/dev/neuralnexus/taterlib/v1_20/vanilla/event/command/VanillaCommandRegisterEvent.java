/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.vanilla.event.command;

import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/** Vanilla implementation of {@link CommandRegisterEvent}. */
public class VanillaCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final CommandDispatcher<CommandSourceStack> dispatcher;
    private final Commands.CommandSelection environment;

    public VanillaCommandRegisterEvent(
            CommandDispatcher<CommandSourceStack> dispatcher,
            Commands.CommandSelection environment) {
        this.dispatcher = dispatcher;
        this.environment = environment;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return environment == Commands.CommandSelection.DEDICATED;
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSourceStack> dispatcher() {
        return dispatcher;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSourceStack> node,
            Object plugin,
            String commandName,
            String... aliases) {
        dispatcher.register(node);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(CommandSourceStack source) {
        return new VanillaCommandSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new VanillaPlayer((net.minecraft.world.entity.player.Player) source.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        dispatcher.register(literalArgumentBuilder);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(literalArgumentBuilder.build()));
        }
    }
}
