/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterapi.command.Command;

import org.bukkit.command.CommandSender;
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
        return command.execute(new BukkitCommandSource(sender), label, args);
    }
}
