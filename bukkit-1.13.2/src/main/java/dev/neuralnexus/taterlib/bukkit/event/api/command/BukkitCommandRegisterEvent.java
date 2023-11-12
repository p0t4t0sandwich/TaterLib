package dev.neuralnexus.taterlib.bukkit.event.api.command;

import dev.neuralnexus.taterlib.bukkit.command.BukkitCommandWrapper;
import dev.neuralnexus.taterlib.common.Plugin;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bukkit implementation of {@link CommandRegisterEvent}.
 */
public class BukkitCommandRegisterEvent implements CommandRegisterEvent {
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        JavaPlugin bukkitPlugin = (JavaPlugin) plugin;
        PluginCommand bukkitCommand = bukkitPlugin.getCommand(command.getName());
        if (bukkitCommand == null) {
            throw new IllegalArgumentException("Command not found.");
        }
        bukkitCommand.setExecutor(new BukkitCommandWrapper(command::execute));
    }
}
