package dev.neuralnexus.taterlib.bungee.event.command;

import dev.neuralnexus.taterlib.bungee.command.BungeeCommandWrapper;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;

import net.md_5.bungee.api.ProxyServer;

/** Bungee implementation of {@link CommandRegisterEvent}. */
public class BungeeCommandRegisterEvent implements CommandRegisterEvent {
    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        ProxyServer.getInstance()
                .getPluginManager()
                .registerCommand(
                        (net.md_5.bungee.api.plugin.Plugin) plugin,
                        new BungeeCommandWrapper(command::execute, command.name()));
    }
}
