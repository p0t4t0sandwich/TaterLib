/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/** Wraps a command callback into a Bukkit CommandExecutor. */
public class BukkitCommandWrapper extends org.bukkit.command.Command {
    private final Command command;

    public BukkitCommandWrapper(Command command) {
        super(command.name());
        this.command = command;
    }

    @Override
    public boolean execute(
            @NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            return command.execute(WrapperRegistry.wrap((Player) sender), label, args);
        }
        return command.execute(new BukkitCommandSender(sender), label, args);
    }
}
