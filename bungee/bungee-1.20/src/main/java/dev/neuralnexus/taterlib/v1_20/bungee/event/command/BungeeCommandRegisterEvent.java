/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.bungee.event.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.v1_20.bungee.command.BungeeCommandWrapper;

import net.md_5.bungee.api.ProxyServer;

/** Bungee implementation of {@link CommandRegisterEvent}. */
public class BungeeCommandRegisterEvent implements CommandRegisterEvent {
    @Override
    public void registerCommand(Command command, String... aliases) {
        ProxyServer.getInstance()
                .getPluginManager()
                .registerCommand(
                        (net.md_5.bungee.api.plugin.Plugin) Loader.instance().plugin(),
                        new BungeeCommandWrapper(command));
    }
}
