package dev.neuralnexus.taterlib.bukkit.commands;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Bukkit implementation of the TaterAPI command.
 */
public class BukkitTaterLibCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            // Check if sender is a player
            boolean isPlayer = sender instanceof Player;
            BukkitPlayer player = isPlayer ? new BukkitPlayer((Player) sender) : null;

            // Execute command
            TaterLibCommand.executeCommand(player, isPlayer, args);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
