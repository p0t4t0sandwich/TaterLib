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
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterloader.Loader;

/** Velocity implementation of {@link CommandRegisterEvent}. */
public class VelocityBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSource> {
    @Override
    public boolean isDedicated() {
        return false;
    }

    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return null;
    }

    @Override
    public CommandSender getSender(CommandSource source) {
        return null;
    }

    @Override
    public Player getPlayer(CommandSource source) {
        return null;
    }

    @Override
    public boolean isPlayer(CommandSource source) {
        return false;
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        CommandManager commandManager =
                ((ProxyServer) Loader.instance().server()).getCommandManager();
        CommandMeta commandMeta =
                commandManager
                        .metaBuilder(commandName)
                        .aliases(aliases)
                        .plugin(Loader.instance().plugin())
                        .build();
        commandManager.register(commandMeta, new BrigadierCommand(node));
    }
}
