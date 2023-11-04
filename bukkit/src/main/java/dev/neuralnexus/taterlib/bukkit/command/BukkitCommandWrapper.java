package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Function;

/**
 * The Bukkit implementation of the TaterAPI command.
 */
public class BukkitCommandWrapper implements CommandExecutor {
    @FunctionalInterface
    public interface CommandCallback {
        boolean execute(CommandSender sender, Command command, String label, String[] args);
    }
    private final CommandCallback callback;

    public BukkitCommandWrapper(CommandCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return callback.execute(sender, command, label, args);
    }
}
