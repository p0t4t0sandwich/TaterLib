package dev.neuralnexus.taterlib.bungee.event.command;

import dev.neuralnexus.taterlib.bungee.BungeeTaterLibPlugin;
import dev.neuralnexus.taterlib.bungee.command.BungeeCommandWrapper;
import dev.neuralnexus.taterlib.common.Plugin;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;

/**
 * Bungee implementation of {@link CommandRegisterEvent}.
 */
public class BungeeCommandRegisterEvent implements CommandRegisterEvent {
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        BungeeTaterLibPlugin.getProxyServer().getPluginManager().registerCommand((net.md_5.bungee.api.plugin.Plugin) plugin, new BungeeCommandWrapper(command::execute, command.getName()));
    }
}
