package dev.neuralnexus.taterlib.v1_20.bukkit.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.command.BukkitCommandWrapper;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit implementation of {@link CommandRegisterEvent}. */
public class BukkitCommandRegisterEvent implements CommandRegisterEvent {
    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        JavaPlugin bukkitPlugin = (JavaPlugin) plugin;
        PluginCommand bukkitCommand = bukkitPlugin.getCommand(command.name());
        if (bukkitCommand == null) {
            throw new IllegalArgumentException("Command not found.");
        }
        bukkitCommand.setExecutor(new BukkitCommandWrapper(command::execute));
    }
}
