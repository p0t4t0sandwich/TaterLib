/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_2_5.bukkit.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.player.BukkitPlayer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** Wraps a command callback into a Bukkit CommandExecutor. */
public class BukkitCommandWrapper extends org.bukkit.command.Command {
    private final Command command;

    public BukkitCommandWrapper(Command command) {
        super(command.name());
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            return command.execute(new BukkitPlayer((Player) sender), label, args);
        }
        return command.execute(new BukkitCommandSender(sender), label, args);
    }
}
