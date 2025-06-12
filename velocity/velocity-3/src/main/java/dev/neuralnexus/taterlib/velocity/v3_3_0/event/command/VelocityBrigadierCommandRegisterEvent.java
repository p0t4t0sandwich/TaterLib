/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;

/** Velocity implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
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
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        CommandManager commandManager =
                ((ProxyServer) MetaAPI.instance().server()).getCommandManager();
        CommandMeta commandMeta =
                commandManager
                        .metaBuilder(commandName)
                        .aliases(aliases)
                        .plugin(Loader.instance().plugin())
                        .build();
        commandManager.register(commandMeta, new BrigadierCommand(node));
    }
}
