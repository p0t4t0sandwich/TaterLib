/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.event.command;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterlib.velocity.v3_3_0.command.VelocityCommandWrapper;

/** Velocity implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
public class VelocityCommandRegisterEvent implements CommandRegisterEvent {
    @Override
    public void registerCommand(Command command, String... aliases) {
        CommandManager commandManager =
                ((ProxyServer) MetaAPI.instance().server()).getCommandManager();
        CommandMeta commandMeta =
                commandManager
                        .metaBuilder(command.name())
                        .aliases(aliases)
                        .plugin(Loader.instance().plugin())
                        .build();
        commandManager.register(commandMeta, new VelocityCommandWrapper(command::execute));
    }
}
