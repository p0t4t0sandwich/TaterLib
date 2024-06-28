/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.CommandSource;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.velocity.v3_3_0.VelocityTaterLibPlugin;

/** Velocity implementation of {@link CommandRegisterEvent}. */
public class VelocityBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSource> {
    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(CommandSource source) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(CommandSource source) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(CommandSource source) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node,
            Object plugin,
            String commandName,
            String... aliases) {
        CommandManager commandManager = VelocityTaterLibPlugin.proxyServer.getCommandManager();
        CommandMeta commandMeta =
                commandManager.metaBuilder(commandName).aliases(aliases).plugin(plugin).build();
        commandManager.register(commandMeta, new BrigadierCommand(node));
    }
}
