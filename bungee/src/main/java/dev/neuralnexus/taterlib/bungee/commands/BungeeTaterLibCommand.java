package dev.neuralnexus.taterlib.bungee.commands;

import dev.neuralnexus.taterlib.bungee.abstractions.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static dev.neuralnexus.taterlib.common.Utils.runTaskAsync;

public class BungeeTaterLibCommand extends Command {
    public BungeeTaterLibCommand() {
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
