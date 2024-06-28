/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_8.bukkit.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.player.BukkitPlayer;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** Wraps a command callback into a Bukkit CommandExecutor. */
public class BukkitCommandWrapper implements CommandExecutor {
    private final Command.Callback callback;

    public BukkitCommandWrapper(Command.Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onCommand(
            CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            return callback.execute(new BukkitPlayer((Player) sender), label, args);
        }
        return callback.execute(new BukkitCommandSender(sender), label, args);
    }
}
