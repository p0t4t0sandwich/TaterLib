package dev.neuralnexus.taterlib.v_1_13_2.bukkit.command;

import dev.neuralnexus.taterlib.v_1_13_2.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.command.Command;

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
