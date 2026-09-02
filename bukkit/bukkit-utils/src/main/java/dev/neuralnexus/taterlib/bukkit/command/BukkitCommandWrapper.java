/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/** Wraps a command callback into a Bukkit CommandExecutor. */
@ToBeLibrary("brigadier-general")
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
