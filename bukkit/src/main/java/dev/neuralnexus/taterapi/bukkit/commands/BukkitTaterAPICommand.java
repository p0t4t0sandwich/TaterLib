package dev.neuralnexus.taterapi.bukkit.commands;

import dev.neuralnexus.taterapi.bukkit.player.BukkitTaterPlayer;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * The Bukkit implementation of the TaterAPI command.
 */
public class BukkitTaterAPICommand implements CommandExecutor, TaterAPICommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AtomicBoolean success = new AtomicBoolean(true);
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                if ((sender instanceof Player)) {
                    // Execute command as player
                    BukkitTaterPlayer player = new BukkitTaterPlayer((Player) sender);
                    player.sendMessage(executeCommand(player, args));
                } else {
                    // Execute command as console
                    TaterAPI.useLogger(ansiiParser(executeCommand(args)));
                }
            } catch (Exception e) {
                success.set(false);
                System.err.println(e);
                e.printStackTrace();
            }
        });
        return success.get();
    }
}
