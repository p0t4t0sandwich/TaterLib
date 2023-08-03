package dev.neuralnexus.taterlib.bungee.commands;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static dev.neuralnexus.taterlib.common.Utils.runTaskAsync;

public class BungeeTaterAPICommand extends Command {
    public BungeeTaterAPICommand() {
        super(TaterLibCommand.getCommandName() + "b");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                boolean isPlayer = sender instanceof ProxiedPlayer;
                BungeePlayer player = isPlayer ? new BungeePlayer((ProxiedPlayer) sender) : null;

                // Execute command
                TaterLibCommand.executeCommand(player, isPlayer, args);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
