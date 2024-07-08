/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.command;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.command.VelocityCommandWrapper;
import dev.neuralnexus.taterloader.Loader;

/** Velocity implementation of {@link CommandRegisterEvent}. */
public class VelocityCommandRegisterEvent implements CommandRegisterEvent {
    /** {@inheritDoc} */
    @Override
    public void registerCommand(Command command, String... aliases) {
        CommandManager commandManager =
                ((ProxyServer) Loader.instance().server()).getCommandManager();
        CommandMeta commandMeta =
                commandManager
                        .metaBuilder(command.name())
                        .aliases(aliases)
                        .plugin(Loader.instance().plugin())
                        .build();
        commandManager.register(commandMeta, new VelocityCommandWrapper(command::execute));
    }
}
