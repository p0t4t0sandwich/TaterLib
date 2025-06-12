/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.event.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.bungee.command.BungeeCommandWrapper;

import net.md_5.bungee.api.ProxyServer;

/** Bungee implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
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
