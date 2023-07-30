package dev.neuralnexus.taterapi.bukkit.commands;

import dev.neuralnexus.taterapi.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * The Bukkit implementation of the TaterAPI command.
 */
public class BukkitTaterAPICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AtomicBoolean success = new AtomicBoolean(true);
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                boolean isPlayer = sender instanceof Player;
                BukkitPlayer player = isPlayer ? new BukkitPlayer((Player) sender) : null;

                // Execute command
                TaterAPICommand.executeCommand(player, isPlayer, args);
            } catch (Exception e) {
                success.set(false);
                System.err.println(e);
                e.printStackTrace();
            }
        });
        return success.get();
    }
}
